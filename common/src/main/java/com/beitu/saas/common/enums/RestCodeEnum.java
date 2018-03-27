package com.beitu.saas.common.enums;

import com.fqgj.common.api.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * https://github.com/bolasblack/http-api-guide
 * <p>
 * Error Description(Chinese)	Error Description(English)
 * 0	成功	Success
 * 1	未知错误	Unknown error
 * 2	服务暂不可用	Service temporarily unavailable
 * 3	未知的方法	Unsupported openapi method
 * 4	接口调用次数已达到设定的上限	Open api request limit reached
 * 5	请求来自未经授权的IP地址	Unauthorized client IP address:%s
 * 6	无权限访问该用户数据	No permission to access domain
 * 7	来自该refer的请求无访问权限	No permission to access domain for this referer
 * 100	请求参数无效	Invalid parameter
 * 101	api key无效	Invalid API key
 * 102	session key无效	Session key invalid or no longer valid
 * 103	call_id参数无效	Invalid/Used call_id parameter
 * 104	无效签名	Incorrect signature
 * 105	请求参数过多	Too many parameters
 * 106	未知的签名方法	Unsupported signature method
 * 107	timestamp参数无效	Invalid/Used timestamp parameter
 * 108	无效的user id	Invalid borrower id
 * 109	无效的用户资料字段名	Invalid borrower info field
 * 110	无效的access token	Access token invalid or no longer valid
 * 111	access token过期	Access token expired
 * 112	session key过期	Session key expired
 * 114   无效的ip参数 Invalid Ip
 * 210	用户不可见	User not visible
 * 211	获取未授权的字段	Unsupported permission
 * 212	没有权限获取用户的email	No permission to access borrower email
 * 800	未知的存储操作错误	Unknown domain store API error
 * 801	无效的操作方法	Invalid operation
 * 802	数据存储空间已超过设定的上限	Data store allowable quota was exceeded
 * 803	指定的对象不存在	Specified object cannot be found
 * 804	指定的对象已存在	Specified object already exists
 * 805	数据库操作出错，请重试	A database error occurred. Please try again
 * 900	访问的应用不存在	No such app exists
 * 950	批量操作已开始，请先调用end_batch接口结束前一个批量操作	begin_batch already called, please make sure to call end_batch first
 * 951	结束批量操作的接口调用不应该在start_batch接口之前被调用	end_batch called before start_batch
 * 952	每个批量调用不能包含多于20个接口调用	Each batch API can not contain more than 20 items
 * 953	该接口不适合在批量调用操作中被使用	Method is not allowed in batch mode
 */

/**
 * HTTP状态码 Error_code Error_msg 备注 500 30600 Internal Server Error 服务器内部错误。 405
 * 30601 Method Not Allowed 不允许的操作（指定了错误的HTTP方法或API）。 400 30602 Request Params
 * Not Valid 请求参数非法。 403 30603 Authentication Failed 权限校验错误 。 402 30604 Quota
 * Use Up Payment Required 无quota。 404 30605 Data Required Not Found 请求数据不存在。
 * 408 30606 Request Time Expires Timeout 请求已超时。 408 30607 Channel Token Timeout
 * channel_token已经过期。 404 30608 Bind Relation Not Found 绑定关系不存在。 404 30609 Bind
 * Number Too Many 绑定数过多。 409 30610 Duplicate Operation 重复操作。 404 30611 Group
 * Not Found 组不存在。
 */
public enum RestCodeEnum implements ErrorCodeEnum {

    TOKEN_NOT_AVAILABLE(700, "Token失效", HttpStatus.UNAUTHORIZED),

    USER_NOT_EXIST_ERROR(10000, "用户名不存在", HttpStatus.OK),
    PARAMTER_SIGN_ERROR(11001, "签名错误", HttpStatus.OK),
    SYSTEM_PARAMTER_ERROR(11000, "系统参数校验失败", HttpStatus.OK),

    NEED_TOKEN_ERROR(20001, "当前操作需要登录", HttpStatus.OK),

    CARRIER_CRAWL_ERROR(40000, "认证失败,请稍后再试！", HttpStatus.INTERNAL_SERVER_ERROR),
    CARRIER_VAILD_VERIFY_CODE_ERROR(40001, "校验验证码失败,请稍后再试！", HttpStatus.OK),
    CARRIEE_REBIND(40002, "运营商授权异常，请重试", HttpStatus.OK),
    REPEAT_REQUEST(800, "正在处理中,请稍后重试", HttpStatus.OK);

    private Integer code;
    private String msg;
    private HttpStatus httpStatus;

    RestCodeEnum(Integer code, String msg, HttpStatus httpStatus) {
        this.code = code;
        this.msg = msg;
        this.httpStatus = httpStatus;
    }

    public Map<String, Object> map() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", code);
        result.put("msg", msg);
        return result;
    }

    public Map<String, Object> map(String message) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", code);
        if (message != null) {
            result.put("msg", message + msg);
        }
        return result;
    }

    public String toString() {
        return code + ":" + msg;
    }

    public Integer getCode() {
        return code;
    }

    public RestCodeEnum setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RestCodeEnum setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public RestCodeEnum setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }
}
