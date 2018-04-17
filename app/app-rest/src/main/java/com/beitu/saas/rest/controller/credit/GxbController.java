package com.beitu.saas.rest.controller.credit;

import com.alibaba.fastjson.JSON;
import com.beitu.saas.app.annotations.SignIgnore;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
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

    @SignIgnore
    @ResponseBody
    @RequestMapping(value = "/eb/callback", method = RequestMethod.POST)
    public Map gxbEcommerceCallBack(HttpServletRequest httpServletRequest) {
        try {
            String jsonStr = uncompress(httpServletRequest.getInputStream());
            LOGGER.info(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map resultMap = new HashMap(2) {{
            put("retCode", 1);
            put("retMsg","成功");
        }};
        return resultMap;
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
}
