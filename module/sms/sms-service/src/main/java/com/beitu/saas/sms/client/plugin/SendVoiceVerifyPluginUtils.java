package com.beitu.saas.sms.client.plugin;

import com.fqgj.common.utils.JSONUtils;
import com.beitu.saas.sms.ro.StateCode;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcTtsNumSinglecallRequest;
import com.taobao.api.response.AlibabaAliqinFcTtsNumSinglecallResponse;

import java.util.Map;

public class SendVoiceVerifyPluginUtils {

    public static String url = "https://eco.taobao.com/router/rest";

    public static void main(String[] args) {
        System.out.println(send("15850516654", "123456"));
    }

    public static StateCode send(String mobile, String verifyCode) {
        try {
            TaobaoClient client = new DefaultTaobaoClient(url, "23371145", "944e2dd1e22ebc03a8f7436e150f3f1c");
            AlibabaAliqinFcTtsNumSinglecallRequest req = new AlibabaAliqinFcTtsNumSinglecallRequest();
            req.setExtend(null);
            req.setTtsParamString("{\"code\":\"" + verifyCode + "\"}");
            req.setCalledNum(mobile);
            req.setCalledShowNum("057128854219");
            req.setTtsCode("TTS_25330250");
            AlibabaAliqinFcTtsNumSinglecallResponse rsp = client.execute(req);

            String respBoy = rsp.getBody();
            if (respBoy.contains("error_response")) {
                return StateCode.FAIL;
            }
            Map responseBoyMap = JSONUtils.json2map(respBoy);
            Map resMap = (Map) responseBoyMap.get("alibaba_aliqin_fc_tts_num_singlecall_response");
            Map result = (Map) resMap.get("result");
            if (Boolean.parseBoolean(result.get("success").toString()) || "ture".equals(result.get("success"))) {
                return StateCode.SUCCESS;
            } else {
                return StateCode.FAIL;
            }
        } catch (Exception e) {
            return StateCode.FAIL;
        }
    }

}
