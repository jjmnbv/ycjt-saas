package com.beitu.saas.credit.exception;

import com.fqgj.common.api.enums.ErrorCodeEnum;
import com.fqgj.exception.common.ApplicationException;


/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/2/26
 * Time: 下午3:52
 */
public class CreditException extends ApplicationException {

    public CreditException(String message) {
        super(message);
    }
    
    public CreditException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreditException(ErrorCodeEnum enums) {
        super(enums);
    }
    
    public CreditException(Integer errorId, String message, Throwable cause) {
        super(errorId, message, cause);
    }
    
    @Override
    public Integer getErrorId() {
        return super.getErrorId();
    }
}
