/**
 * @Title: TongDunHttpClient.java
 * @author zxy
 * @date 2015年12月17日 下午4:34:13
 */
package com.beitu.saas.risk.handler.platform.tongdun;

import com.alibaba.fastjson.JSONObject;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.risk.domain.enums.ErrorCodeEnums;
import com.beitu.saas.risk.domain.exception.BizException;
import com.beitu.saas.risk.helpers.JSONUtils;
import com.beitu.saas.risk.helpers.MySSLProtocolSocketFactory;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zxy
 * @ClassName: TongDunHandler
 * @Description: 同盾检测Client
 * @date 2015年12月17日 下午4:34:13
 */

@Component
public class TongDunHandler {

    private static final Log LOGGER = LogFactory.getLog(TongDunHandler.class);

    @Autowired
    private ConfigUtil configUtil;

    public String getTongDunReportId(Map<String, String> dataMap) throws Exception {
        Protocol myhttps = new Protocol("https", new MySSLProtocolSocketFactory(), 443);
        Protocol.registerProtocol("https", myhttps);
        HttpClient httpclient = new HttpClient();
        StringBuffer urlBuf = new StringBuffer(configUtil.getTongdunUrl()).append("?partner_code=")
                .append(configUtil.getTongdunPartnerCode()).append("&partner_key=").append(configUtil.getTongdunPartnerKey()).append("&app_name=").append(configUtil.getTongdunAppName());
        PostMethod postmethod = new PostMethod(urlBuf.toString());
        postmethod.addParameter("name", dataMap.get("real_name"));
        postmethod.addParameter("id_number", dataMap.get("identityNo"));
        postmethod.addParameter("mobile", dataMap.get("mobile"));

        if (null != dataMap.get("contact1_mobile")) {
            postmethod.addParameter("contact1_mobile", dataMap.get("contact1_mobile"));
        }
        postmethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");//这一句是设置编码的格式
        int resultCode = httpclient.executeMethod(postmethod);
        String returnCode = postmethod.getResponseBodyAsString();
        if (resultCode != 200) {
            throw new BizException(ErrorCodeEnums.PLATFORM_SUBSTRIPTION_RESULT_ERROR);
        }

        LOGGER.info("-------tongdun------request:{}-----response:{}", dataMap, returnCode);

        String reportId = returnCode.substring(returnCode.lastIndexOf(":") + 2, returnCode.lastIndexOf('"'));
        return reportId;
    }

    //查询同盾检测结果
    public JSONObject getTongDunReportMap(String reportId) throws Exception {
        StringBuffer queryUrlBuf = new StringBuffer(configUtil.getTongdunQueryUrl()).append("?partner_code=")
                .append(configUtil.getTongdunPartnerCode()).append("&partner_key=").append(configUtil.getTongdunPartnerKey())
                .append("&app_name=").append(configUtil.getTongdunAppName())
                .append("&report_id=").append(reportId);
        GetMethod getMethod = new GetMethod(queryUrlBuf.toString());
        getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");//这一句是设置编码的格式
        HttpClient httpclient = new HttpClient();
        int resultCode = httpclient.executeMethod(getMethod);
        if (resultCode != 200) {
            throw new BizException(ErrorCodeEnums.PLATFORM_SUBSTRIPTION_RESULT_ERROR);
        }
        String queryReport = getMethod.getResponseBodyAsString();
//        InputStream inputStream = getMethod.getResponseBodyAsStream();
//        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
//        StringBuffer result = new StringBuffer();
//        String queryReport= "";
//        while((queryReport = br.readLine()) != null){
//            result .append(queryReport);
//        }

        JSONObject tundunMap = JSONObject.parseObject(queryReport);
        tundunMap.put("queryReport", queryReport);
        return tundunMap;
    }
}