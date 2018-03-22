package com.beitu.saas.risk.domain.platform.vo;

import com.fqgj.common.api.Response;
import com.fqgj.common.api.enums.BasicMsgCodeEnum;
import com.fqgj.common.api.enums.MsgCodeEnum;
import com.beitu.saas.risk.domain.platform.TripleServiceBaseOutput;

public class TripleServiceResult extends Response {

    public TripleServiceResult() {
        super();
    }

    @Override
    public boolean isSuccess() {
        return this.get("success").toString().equals("true");
    }

    @Override
    public TripleServiceBaseOutput getData() {
        return (TripleServiceBaseOutput) get("data");
    }

    public TripleServiceResult setData(TripleServiceBaseOutput tripleServiceBaseOutput) {
        put("data", tripleServiceBaseOutput);
        return this;
    }

    public String getDealNo() {
        return (String) get("dealNo");
    }

    public TripleServiceResult setDealNo(String dealNo) {
        put("dealNo", dealNo);
        return this;
    }

    public static TripleServiceResult error() {
        return error(BasicMsgCodeEnum.INTERNAL_SERVER_ERROR);
    }

    public static TripleServiceResult error(MsgCodeEnum msgCodeEnum) {
        TripleServiceResult r = new TripleServiceResult();
        r.put("code", msgCodeEnum.getCode());
        r.put("msg", msgCodeEnum.getMsg());
        r.put("success", false);
        return r;
    }

    public static TripleServiceResult error(MsgCodeEnum msgCodeEnum, String msg) {
        TripleServiceResult r = error(msgCodeEnum);
        r.put("msg", msg);
        return r;
    }

    public static TripleServiceResult ok() {
        return ok(BasicMsgCodeEnum.SUCCESS);
    }

    public static TripleServiceResult ok(MsgCodeEnum msgCodeEnum) {
        TripleServiceResult r = new TripleServiceResult();
        r.put("code", msgCodeEnum.getCode());
        r.put("msg", msgCodeEnum.getMsg());
        r.put("success", true);
        return r;
    }

    public static TripleServiceResult ok(MsgCodeEnum msgCodeEnum, String msg) {
        TripleServiceResult r = ok(msgCodeEnum);
        r.put("msg", msg);
        return r;
    }

}
