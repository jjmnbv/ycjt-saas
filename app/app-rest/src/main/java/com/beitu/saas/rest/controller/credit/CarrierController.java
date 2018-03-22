package com.beitu.saas.rest.controller.credit;

import com.alibaba.fastjson.JSON;
import com.beitu.saas.app.annotations.VisitorAccessible;
import com.beitu.saas.app.api.DataApiResponse;
import com.beitu.saas.app.application.credit.CarrierApplication;
import com.beitu.saas.app.application.credit.vo.CarrierH5CallbackVo;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.borrower.client.SaasBorrowerService;
import com.beitu.saas.borrower.consts.UserProfileConsts;
import com.beitu.saas.borrower.domain.SaasBorrowerVo;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.consts.RedisKeyConsts;
import com.beitu.saas.common.consts.TimeConsts;
import com.beitu.saas.rest.controller.credit.response.CarrierH5Response;
import com.beitu.saas.risk.domain.carrier.h5.enums.CarrierH5StatusEnum;
import com.beitu.saas.risk.domain.carrier.h5.enums.CarrierH5TypeEnum;
import com.beitu.saas.risk.domain.enums.ErrorCodeEnums;
import com.beitu.saas.risk.domain.exception.BizException;
import com.beitu.saas.risk.handler.carrier.h5.tianji.CarrierH5TianjiHandler;
import com.beitu.saas.risk.handler.carrier.h5.tianji.enums.CarrierTianjiStatusEnum;
import com.beitu.saas.risk.handler.carrier.h5.tianji.vo.CarrierTianjiCacheVo;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.exception.common.ApplicationException;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author linanjun
 * @create 2018/3/22 下午5:24
 * @description
 */
@Controller
@RequestMapping("/credit/carrier")
public class CarrierController {

    private static final Log LOGGER = LogFactory.getLog(CarrierController.class);

    @Autowired
    private CarrierApplication carrierApplication;

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private ConfigUtil configUtil;

    @Autowired
    private CarrierH5TianjiHandler carrierH5TianjiHandler;

    @Autowired
    private SaasBorrowerService saasBorrowerService;

    @RequestMapping(value = "/h5/get", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "H5运营商认证地址获取接口", response = CarrierH5Response.class)
    public DataApiResponse<CarrierH5Response> getCarrierH5() {
        SaasBorrowerVo saasBorrowerVo = RequestLocalInfo.getCurrentBorrower();
        String url = carrierApplication.getCarrierH5Url(saasBorrowerVo.getBorrowerCode(), saasBorrowerVo.getMobile());
        return new DataApiResponse<>(new CarrierH5Response(url));
    }

    @VisitorAccessible
    @RequestMapping(value = "/h5/crawling", method = RequestMethod.GET)
    public String crawlingNotify(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String taskId = request.getParameter("outUniqueId");
        String success = "false";
        if (StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(taskId)) {
            SaasBorrowerVo saasBorrowerVo = saasBorrowerService.getByBorrowerCode(userId);
            if (saasBorrowerVo != null) {
                if (carrierApplication.carrierTaskAndUserMatch(userId, saasBorrowerVo.getMobile(), taskId)) {
                    redisClient.set(RedisKeyConsts.H5_CARRIER_CRAWLING, taskId, TimeConsts.TEN_MINUTES, userId);
                    success = "true";
                }
            }
        }
        return "redirect:" + configUtil.getAddressURLPrefix() + UserProfileConsts.H5_CARRIER_RESULT_URL + "?success=" + success;
    }

    @VisitorAccessible
    @ResponseBody
    @RequestMapping(value = "/callback/{carrierType}", consumes = "multipart/form-data", method = RequestMethod.POST)
    public String carrierCallback(@PathVariable(value = "carrierType") Integer carrierType,
                                  HttpServletRequest httpServletRequest) {
        Map<String, String> resultMap = new HashMap<>();
        try {
            CarrierH5TypeEnum carrierH5TypeEnum = CarrierH5TypeEnum.getEnumbyType(carrierType);
            switch (carrierH5TypeEnum) {
                case CARRIER_TIANJI:
                    tianjiCallback(httpServletRequest);
                    resultMap.put("status", "success");
                    break;
                default:
                    resultMap.put("status", "error");
                    resultMap.put("msg", ErrorCodeEnums.CARRIER_TIMEOUT_ERROR.getDesc());
                    break;
            }
        } catch (BizException e) {
            //同步参数错误
            resultMap.put("status", "error");
            resultMap.put("msg", e.getMessage());
        } catch (Exception e) {
            //同步过程系统异常
            resultMap.put("status", "error");
            resultMap.put("msg", ErrorCodeEnums.CARRIER_TIMEOUT_ERROR.getDesc());
        }
        return JSON.toJSONString(resultMap);
    }

    private void tianjiCallback(HttpServletRequest httpServletRequest) {
        MultipartResolver resolver = new CommonsMultipartResolver(httpServletRequest.getSession().getServletContext());
        MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(httpServletRequest);
        String userId = multipartRequest.getParameter("userId");
        String outUniqueId = multipartRequest.getParameter("outUniqueId");
        String state = multipartRequest.getParameter("state");
        String account = multipartRequest.getParameter("account");
        String searchId = multipartRequest.getParameter("search_id");
        LOGGER.info("=============carrier callback request=============参数：userId:{};outUniqueId:{};state:{};account:{};search_id:{}", userId, outUniqueId, state, account, searchId);
        if (StringUtils.isEmpty(outUniqueId) || StringUtils.isEmpty(searchId)) {
            throw new ApplicationException("outUniqueId can not be empty");
        }
        CarrierTianjiCacheVo carrierTianjiCacheVo = carrierH5TianjiHandler.getCacheData(outUniqueId);
        String status = "";
        String reportData = "";
        if (StringUtils.isNotEmpty(state)) {
            if (state.equals(CarrierTianjiStatusEnum.INIT.getType())
                    || state.equals(CarrierTianjiStatusEnum.LOGIN.getType())
                    || state.equals(CarrierTianjiStatusEnum.CRAWL.getType())) {
                return;
            }
            if (state.equals(CarrierTianjiStatusEnum.REPORT.getType())) {
                for (int i = 0; i < 3; i++) {
                    try {
                        reportData = carrierH5TianjiHandler.queryCarrierInfo(searchId, carrierTianjiCacheVo);
                        status = CarrierH5StatusEnum.DONE_SUCCESS.getName();
                        break;
                    } catch (Exception e) {
                        LOGGER.info("queryCarrierInfo error,userCode: {},i:{} e:", carrierTianjiCacheVo.getUserCode(), i, e);
                    }
                }
            }
        }
        CarrierH5CallbackVo h5CallbackVo = new CarrierH5CallbackVo();
        h5CallbackVo.setUserCode(userId);
        h5CallbackVo.setTaskId(outUniqueId);
        h5CallbackVo.setStatus(status);
        h5CallbackVo.setData(reportData);
        h5CallbackVo.setCarrierType(CarrierH5TypeEnum.CARRIER_TIANJI);
        carrierApplication.carrierH5Callback(h5CallbackVo);
    }

}