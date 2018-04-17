package com.beitu.saas.rest.controller.credit;

import com.alibaba.fastjson.JSON;
import com.beitu.saas.app.annotations.SignIgnore;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.consts.RedisKeyConsts;
import com.beitu.saas.common.handle.oss.OSSService;
import com.beitu.saas.common.utils.NetworkUtil;
import com.beitu.saas.common.utils.OrderNoUtil;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.common.api.Response;
import com.fqgj.common.utils.JSONUtils;
import com.fqgj.common.utils.MD5;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @SignIgnore
    @ResponseBody
    @RequestMapping(value = "/eb/callback", method = RequestMethod.POST)
    public Map gxbEcommerceCallBack(HttpServletRequest httpServletRequest) {

        //TODO 鉴权
        try {
            String ipAddress = NetworkUtil.getIpAddress(httpServletRequest);
            List<String> ipList = Arrays.asList(configUtil.getGXBPushIP().split(","));
            if (ipList.contains(ipAddress)){
                return new HashMap(2) {{
                    put("retCode", 2);
                    put("retMsg", "失败");
                }};
            }
            LOGGER.info("========"+ipAddress);
            String jsonStr = uncompress(httpServletRequest.getInputStream());
            Map<String, Object> responseMap =  JSONUtils.json2map(jsonStr);
            String userCode = (String) responseMap.get("sequenceNo");
            String authJson = (String) responseMap.get("authJson");

            String url = ossService.uploadFile(getEbUrl(userCode), authJson);
            LOGGER.info("========"+url);

            LOGGER.info("========"+jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("gxbEcommerceCallBack---{}", e);
            return new HashMap(2) {{
                put("retCode", 2);
                put("retMsg", "失败");
            }};
        }
        Map resultMap = new HashMap(2) {{
            put("retCode", 1);
            put("retMsg", "成功");
        }};
        return resultMap;
    }

    @RequestMapping(value = "/eb/notice", method = RequestMethod.POST)
    public Response ecommerceSuccess() {
        String platform = RequestLocalInfo.getCurrentAdmin().getRequestBasicInfo().getPlatform();

       // if ()
        RequestLocalInfo.getCurrentAdmin().getSaasAdmin();
       // redisClient.set(RedisKeyConsts.SAAS_GXB_ECOMMERCE,token,);
        return Response.ok();
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
