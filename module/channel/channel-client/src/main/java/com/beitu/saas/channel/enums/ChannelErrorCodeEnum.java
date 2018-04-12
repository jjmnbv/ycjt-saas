package com.beitu.saas.channel.enums;

import com.fqgj.common.api.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/22
 * Time: 下午2:44
 */
public enum ChannelErrorCodeEnum implements ErrorCodeEnum {
    CHANNEL_PARAM_INVALID(10001, "服务器内部错误: 创建渠道失败!", HttpStatus.OK),
    MODULE_PARAM_INVALID(10002, "创建模块失败, 传入参数异常", HttpStatus.OK),
    MODULE_ITEM_PARAM_INVALID(10003, "创建模块字段失败, 传入参数异常", HttpStatus.OK),
    DISABLE_CHANNEL(10004, "渠道不可用", HttpStatus.OK),
    CHANNEL_MODULE(10005, "渠道未进行风控配置", HttpStatus.OK);

    private Integer code;

    private String msg;

    private HttpStatus httpStatus;


    ChannelErrorCodeEnum(Integer code, String desc, HttpStatus httpStatus) {
        this.code = code;
        this.msg = desc;
        this.httpStatus = httpStatus;
    }

    public Integer getCode() {
        return code;
    }

    public ChannelErrorCodeEnum setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ChannelErrorCodeEnum setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ChannelErrorCodeEnum setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }
}
