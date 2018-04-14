package com.beitu.saas.risk.handler.platform.rongScore;

import com.alibaba.fastjson.JSONObject;
import com.beitu.saas.risk.domain.enums.ErrorCodeEnums;
import com.beitu.saas.risk.domain.exception.BizException;
import com.beitu.saas.risk.domain.platform.rongScore.TripleRongScoreOutput;
import com.beitu.saas.risk.domain.platform.rongScore.request.OpenapiRequest;
import com.beitu.saas.risk.domain.platform.rongScore.utils.RequestUtil;
import com.beitu.saas.risk.helpers.JSONUtils;
import org.springframework.stereotype.Component;

@Component
public class RongHandler {
    private String method;
    private OpenapiRequest openapiRequest = new OpenapiRequest();

    public JSONObject execute() throws Exception {
        String result = RequestUtil.request(this.method, this.openapiRequest.getParams());
        if (result == null || result.length() == 0) {
            throw new BizException(ErrorCodeEnums.PLATFORM_SUBSTRIPTION_RESULT_ERROR);
        }
        JSONObject jsonRet = JSONObject.parseObject(result);
        if (jsonRet == null) {
            throw new BizException(ErrorCodeEnums.PLATFORM_SUBSTRIPTION_RESULT_ERROR);
        }
        String code = jsonRet.getString("error");
        if (!"200".equals(code)) {
            String msg = jsonRet.getString("msg");
            throw new BizException(msg);
        }
        return jsonRet;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setField(String key, String value) {
        this.openapiRequest.putParam(key, value);
    }


    public TripleRongScoreOutput getRongResult(String orderNo) throws Exception {
        RongHandler sample = new RongHandler();
        sample.setMethod("tianji.api.taojinyunreport.customscore");
        sample.setField("orderNo", orderNo);
        JSONObject result = sample.execute();

        TripleRongScoreOutput rongResponse = JSONUtils.json2pojo(result.toString(), TripleRongScoreOutput.class);
        return rongResponse;
    }

    public static void main(String[] args) throws Exception {
        RongHandler sample = new RongHandler();
        sample.setMethod("tianji.api.taojinyunreport.customscore");
        sample.setField("orderNo", "250996963769431");
        JSONObject ret = sample.execute();
        TripleRongScoreOutput rongResponse = JSONUtils.json2pojo(ret.toString(), TripleRongScoreOutput.class);
        System.out.println("ret:" + ret.toString());

    }
}
