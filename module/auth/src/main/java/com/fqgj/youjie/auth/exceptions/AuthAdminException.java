package com.fqgj.youjie.auth.exceptions;

import com.fqgj.common.api.enums.ErrorCodeEnum;
import com.fqgj.exception.common.ApplicationException;


/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/2/26
 * Time: 下午3:52
 */
public class AuthAdminException extends ApplicationException {

    public AuthAdminException(String message) {
        super(message);
    }

    public AuthAdminException(ErrorCodeEnum enums) {
        super(enums);
    }

    /*public AuthAdminException(ErrorCodeEnum enums, String message) {
        super(enums, message);
    }*/

    public AuthAdminException(String message, Throwable cause) {
        super(message, cause);
    }


    @Override
    public Integer getErrorId() {
        return super.getErrorId();
    }
}
