package com.beitu.saas.rest.controller.credit;

import com.beitu.saas.app.annotations.SignIgnore;
import com.beitu.saas.app.common.RequestBasicInfo;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.borrower.client.SaasBorrowerRealInfoService;
import com.beitu.saas.borrower.domain.SaasBorrowerRealInfoVo;
import com.beitu.saas.borrower.domain.SaasBorrowerVo;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.consts.RedisKeyConsts;
import com.beitu.saas.common.consts.TimeConsts;
import com.beitu.saas.common.handle.oss.OSSService;
import com.beitu.saas.common.utils.NetworkUtil;
import com.beitu.saas.common.utils.OrderNoUtil;
import com.beitu.saas.credit.client.SaasGxbEbService;
import com.beitu.saas.credit.entity.SaasGxbEb;
import com.beitu.saas.finance.client.SaasCreditHistoryService;
import com.beitu.saas.finance.client.SaasMerchantCreditInfoService;
import com.beitu.saas.finance.client.enums.CreditConsumeEnum;
import com.beitu.saas.intergration.risk.RiskEcommerceService;
import com.beitu.saas.intergration.risk.param.GXBEcommerceCrawlingParam;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.common.api.Response;
import com.fqgj.common.api.exception.ApiErrorException;
import com.fqgj.common.utils.JSONUtils;
import com.fqgj.common.utils.MD5;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;
import java.util.zip.GZIPInputStream;

/**
 * @author xiaochong
 * @create 2018/4/16 下午9:37
 * @description
 */
@Controller
@RequestMapping("/gxb")
public class GxbController {

    private static final Log LOGGER = LogFactory.getLog(GxbController.class);
    @Autowired
    private RedisClient redisClient;

    @Autowired
    private OSSService ossService;

    @Autowired
    private ConfigUtil configUtil;

    @Autowired
    private SaasGxbEbService saasGxbEbService;

    @Autowired
    private RiskEcommerceService riskEcommerceService;

    @Autowired
    private SaasBorrowerRealInfoService saasBorrowerRealInfoService;

    @Autowired
    private SaasCreditHistoryService saasCreditHistoryService;

    @SignIgnore
    @ResponseBody
    @RequestMapping(value = "/eb/callback", method = RequestMethod.POST)
    public Map gxbEcommerceCallBack(HttpServletRequest httpServletRequest) {
        Map successMap = new HashMap(2) {{
            put("retCode", 1);
            put("retMsg", "成功");
        }};
        Map errorMap = new HashMap(2) {{
            put("retCode", 2);
            put("retMsg", "失败");
        }};
        try {
            String ipAddress = NetworkUtil.getIpAddress(httpServletRequest);
            List<String> ipList = Arrays.asList(configUtil.getGXBPushIP().split(","));
            if (!ipList.contains(ipAddress)) {
                return errorMap;
            }
            String jsonStr = uncompress(httpServletRequest.getInputStream());
            Map<String, Object> responseMap = JSONUtils.json2map(jsonStr);
            String userCode = (String) responseMap.get("sequenceNo");
            String authJson = (String) responseMap.get("authJson");
            String url = ossService.uploadFile(getEbUrl(userCode), authJson);
            SaasGxbEb saasGxbEb = new SaasGxbEb();
            saasGxbEb.setBorrowerCode(userCode);
            saasGxbEb.setJsonUrl(url);
            saasGxbEb.setTaskToken((String) responseMap.get("token"));
            saasGxbEbService.saveGXBEbTop(saasGxbEb);
            String merchantCode = redisClient.get(RedisKeyConsts.SAAS_GXB_ECOMMERCE_TOKN, (String) responseMap.get("token"));
            redisClient.expire(RedisKeyConsts.SAAS_GXB_ECOMMERCE_TOKN, TimeConsts.ONE_MINUTE, (String) responseMap.get("token"));
            saasCreditHistoryService.addExpenditureCreditHistory(merchantCode, (String) responseMap.get("name"), CreditConsumeEnum.RISK_EB);
            LOGGER.info("公信宝电商成功--------{}", url);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("公信宝电商回调失败--------{}", e);
            return errorMap;
        }
        return successMap;
    }

    @RequestMapping(value = "/eb/url", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("获取电商认证url")
    public Response getGxbEbUrl() {
        SaasBorrowerVo saasBorrower = RequestLocalInfo.getCurrentAdmin().getSaasBorrower();
        RequestBasicInfo requestBasicInfo = RequestLocalInfo.getCurrentAdmin().getRequestBasicInfo();
        String borrowerCode = saasBorrower.getBorrowerCode();
        SaasBorrowerRealInfoVo borrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(borrowerCode);
        if (borrowerRealInfoVo == null) {
            throw new ApiErrorException("请先实名");
        }
        Object token = redisClient.get(RedisKeyConsts.SAAS_GXB_ECOMMERCE, borrowerCode);
        if (token != null) {
            throw new ApiErrorException("认证中...");
        }
        if (!saasGxbEbService.canAuthEbByBorrowerCode(borrowerCode)) {
            throw new ApiErrorException("电商有效期内，无需认证");
        }
        StringBuilder sb = new StringBuilder(configUtil.getApiWebPath());
        sb.append("/gxb/eb/notice/").append(borrowerCode).append("/").append(requestBasicInfo.getPlatform()).append("/").append(requestBasicInfo.getChannel()).append("/");
        GXBEcommerceCrawlingParam param = new GXBEcommerceCrawlingParam();
        param.setIdcard(borrowerRealInfoVo.getIdentityCode());
        param.setName(borrowerRealInfoVo.getName());
        param.setPhone(saasBorrower.getMobile());
        param.setReturnUrl(sb.toString());
        param.setUserCode(borrowerCode);
        param.setMerchantCode(saasBorrower.getMerchantCode());
        return Response.ok().putData(riskEcommerceService.getEcommerceCrawlingUrl(param));
    }

    @RequestMapping(value = "/eb/notice/{code}/{platform}/{channelCode}/{token}", method = RequestMethod.GET)
    @SignIgnore
    public String ecommerceSuccess(@PathVariable("code") String code, @PathVariable("platform") String platform, @PathVariable("channelCode") String channelCode, @PathVariable("token") String token, @RequestParam String success, HttpServletRequest httpServletRequest) {
        String ipAddress = null;
        try {
            ipAddress = NetworkUtil.getIpAddress(httpServletRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.info("ecommerceSuccess--------" + ipAddress);
        LOGGER.info("ecommerceSuccess---------------" + success);
        if (Objects.equals("1", success)) {
            Object obj1 = redisClient.get(RedisKeyConsts.SAAS_GXB_ECOMMERCE_TOKN, token);
            if (obj1 == null) {
                return "redirect:" + "https://www.baidu.com";
            }
            redisClient.del(RedisKeyConsts.SAAS_GXB_ECOMMERCE_TOKN, token);
            Object obj = redisClient.get(RedisKeyConsts.SAAS_GXB_ECOMMERCE, code);
            if (obj != null) {
                return "redirect:" + "https://www.baidu.com";
            }
            redisClient.set(RedisKeyConsts.SAAS_GXB_ECOMMERCE, token, TimeConsts.FIFTEEN_SECONDS, code);
        }
        if ("web".equals(platform)) {
            return "redirect:" + "https://www.baidu.com";
        }
        return "redirect:" + configUtil.getH5AddressURL() + "?channel=" + channelCode
                + "#/formList";
    }

    public String uncompress(InputStream inputStream) throws IOException {
        InputStream gzipInputStream = new GZIPInputStream(inputStream, 1024 * 1024);
        Reader reader = new InputStreamReader(gzipInputStream, "UTF-8");
        char[] buffer = new char[10240];
        StringWriter writer = new StringWriter();
        for (int length = 0; (length = reader.read(buffer)) > 0; ) {
            writer.write(buffer, 0, length);
        }
        writer.close();
        reader.close();
        gzipInputStream.close();
        inputStream.close();
        return writer.toString();
    }

    private String getEbUrl(String userCode) {
        StringBuilder filePath = new StringBuilder("ecommerce/");
        if (configUtil.isServerTest()) {
            filePath.append("test/");
        }
        filePath.append(userCode).append("/").append(MD5.md5(OrderNoUtil.makeOrderNum()));
        return filePath.toString();
    }
}
