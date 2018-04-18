package com.beitu.saas.rest.controller.credit;

import com.beitu.saas.app.annotations.SignIgnore;
import com.beitu.saas.app.common.RequestBasicInfo;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.borrower.domain.SaasBorrowerVo;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.consts.RedisKeyConsts;
import com.beitu.saas.common.consts.TimeConsts;
import com.beitu.saas.common.handle.oss.OSSService;
import com.beitu.saas.common.utils.NetworkUtil;
import com.beitu.saas.common.utils.OrderNoUtil;
import com.beitu.saas.credit.client.SaasGxbEbService;
import com.beitu.saas.credit.entity.SaasGxbEb;
import com.beitu.saas.intergration.risk.RiskEcommerceService;
import com.beitu.saas.intergration.risk.RiskIntergrationService;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * @author xiaochong
 * @create 2018/4/16 下午9:37
 * @description
 */
@RestController
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
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("公信宝电商回调失败--------{}", e);
            return errorMap;
        }
        return successMap;
    }

    @RequestMapping(value = "/eb/url", method = RequestMethod.GET)
    @ApiOperation("获取电商认证url")
    public Response getGxbEbUrl() {
        SaasBorrowerVo saasBorrower = RequestLocalInfo.getCurrentAdmin().getSaasBorrower();
        RequestBasicInfo requestBasicInfo = RequestLocalInfo.getCurrentAdmin().getRequestBasicInfo();
        String borrowerCode = saasBorrower.getBorrowerCode();
        Object token = redisClient.get(RedisKeyConsts.SAAS_GXB_ECOMMERCE, borrowerCode);
        if (token != null) {
            throw new ApiErrorException("认证中...");
        }
        if (!saasGxbEbService.canAuthEbByBorrowerCode(borrowerCode)) {
            throw new ApiErrorException("电商有效期内，无需认证");
        }
        StringBuilder sb = new StringBuilder(configUtil.getApiWebPath());
        sb.append("/eb/notice/").append(borrowerCode).append("/").append(requestBasicInfo.getPlatform()).append("/").append(requestBasicInfo.getChannel()).append("/");
        GXBEcommerceCrawlingParam param = new GXBEcommerceCrawlingParam();
        param.setIdcard("430224197111173910");
        param.setName("xxxx");
        param.setPhone(saasBorrower.getMobile());
        param.setReturnUrl(sb.toString());
        param.setUserCode(borrowerCode);
        return Response.ok().putData(riskEcommerceService.getEcommerceCrawlingUrl(param));
    }

    @RequestMapping(value = "/eb/notice/{code}/{platform}/{channelCode}/{token}", method = RequestMethod.GET)
    @SignIgnore
    public String ecommerceSuccess(@PathVariable("token") String token, @PathVariable("code") String code, HttpServletRequest httpServletRequest, @PathVariable("platform") String platform, @PathVariable("channelCode") String channelCode) {
        String ipAddress = null;
        try {
            ipAddress = NetworkUtil.getIpAddress(httpServletRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.info("ecommerceSuccess--------" + ipAddress);
        List<String> ipList = Arrays.asList(configUtil.getGXBPushIP().split(","));
        if (!ipList.contains(ipAddress)) {
            return "error";
        }
        redisClient.set(RedisKeyConsts.SAAS_GXB_ECOMMERCE, token, TimeConsts.FIFTEEN_SECONDS, code);
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
