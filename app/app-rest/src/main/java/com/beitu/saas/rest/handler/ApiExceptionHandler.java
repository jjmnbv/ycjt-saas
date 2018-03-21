package com.beitu.saas.rest.handler;

import com.fqgj.common.api.ApiResponse;
import com.fqgj.common.api.enums.BasicErrorCodeEnum;
import com.fqgj.common.api.exception.ApiErrorException;
import com.fqgj.common.api.exception.ApiIllegalArgumentException;
import com.fqgj.exception.common.ApplicationException;
import com.fqgj.exception.common.SystemException;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import com.weibo.api.motan.exception.MotanFrameworkException;
import com.weibo.api.motan.exception.MotanServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.reflect.UndeclaredThrowableException;
import java.nio.file.AccessDeniedException;
import java.util.List;

/**
 * @author xiaochong
 * @Create 2017/9/11 下午7:27
 * @Description
 */
@ControllerAdvice
public class ApiExceptionHandler {

    private static final Log LOGGER = LogFactory.getLog(ApiExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ApiResponse handleAllException(Exception ex) {
        LOGGER.error("异常信息为：{}", ex);
        ApiResponse response = new ApiResponse(BasicErrorCodeEnum.INTERNAL_SERVER_ERROR);
        if (ex instanceof SystemException) {
            SystemException error = (SystemException) ex.getCause();
            response.setCode(error.getErrorCode());
            response.setMsg(error.getErrorMessage());
            return response;
        } else if (ex instanceof UndeclaredThrowableException) {
            if(((UndeclaredThrowableException) ex).getUndeclaredThrowable().getCause()instanceof ApiErrorException){
                ApiErrorException error = (ApiErrorException) ((UndeclaredThrowableException) ex).getUndeclaredThrowable().getCause();
                response.setCode(error.getErrorId());
                response.setMsg(error.getMessage());
                return response;
            }
            if (((UndeclaredThrowableException) ex).getUndeclaredThrowable() instanceof SystemException) {
                SystemException systemException = (SystemException)((UndeclaredThrowableException) ex).getUndeclaredThrowable();
                response.setCode(systemException.getErrorCode());
                response.setMsg(systemException.getErrorMessage());
                return response;
            }
        } else if (ex instanceof ApplicationException) {
            ApplicationException error = (ApplicationException) ex;
            response.setCode(error.getErrorId());
            response.setMsg(error.getMessage());
            return response;
        }else if (ex instanceof ApiErrorException){
            ApiErrorException error = (ApiErrorException) ex;
            response.setCode(error.getErrorId());
            response.setMsg(error.getMessage());
            return response;
        }
        
        return response;
    }
    
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseBody
//    public ApiResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        ApiResponse response = new ApiResponse(BasicErrorCodeEnum.PARAM_VALID_ERROR);
//        BindingResult result = e.getBindingResult();
//        StringBuffer sb = new StringBuffer();
//        for (FieldError error : result.getFieldErrors()) {
//            String field = error.getField();
//            String code = error.getDefaultMessage();
//            String message = String.format("%s:%s", field, code);
//            sb.append(message);
//        }
//        response.setMsg(sb.toString());
//        return response;
//
//    }

    @ExceptionHandler(value = ApplicationException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ApiResponse applicationErrorHandler(ApplicationException e) throws Exception {
        LOGGER.warn("异常信息为：{}", e);
        return new ApiResponse(e.getErrorId(), e.getMessage());
    }
    
    
    @ExceptionHandler(value = ApiIllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ApiResponse ApiIllegalArgumentErrorHandler(ApiIllegalArgumentException e) throws Exception {
        LOGGER.warn("异常信息为：{}", e);
        return new ApiResponse(e.getErrorId(), e.getMessage());
    }

    /**
     * 业务级异常的捕获
     *
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ApiResponse AccessErrorHandler(AccessDeniedException e) throws Exception {
        LOGGER.warn("异常信息为：{}", e);
        return new ApiResponse(BasicErrorCodeEnum.NO_ACCESS_RIGHT.getCode(), "没有权限进行该操作");
    }
    
    
    /**
     * post请求参数的验证 json方式提交校验时会报这个错
     *
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ApiResponse errorResponse(MethodArgumentNotValidException e) {
        LOGGER.warn("异常信息为：{}", e);
        BasicErrorCodeEnum errorCode = BasicErrorCodeEnum.PARAM_VALID_ERROR;
        if (e.getBindingResult().hasErrors()) {
            List<ObjectError> errors = e.getBindingResult().getAllErrors();
            errorCode.setDesc(errors.get(errors.size() - 1).getDefaultMessage());
        }
        return new ApiResponse(errorCode);
    }
    
    
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ApiResponse HttpMessageNotReadableErrorHandler(HttpMessageNotReadableException e) throws Exception {
        LOGGER.warn("异常信息为：{}", e);
        return new ApiResponse(BasicErrorCodeEnum.PARAM_RESOLVE_ERROR.getCode(), "业务请求参数有误，请核对");
    }
    
    @ExceptionHandler(value = MotanFrameworkException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ApiResponse errorResponse(MotanFrameworkException e) {
        LOGGER.warn("异常信息为：{}", e);
        return new ApiResponse(BasicErrorCodeEnum.MOTAN_SERVICE_ERROR);
    }
    
    @ExceptionHandler(value = MotanServiceException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ApiResponse errorResponse(MotanServiceException e) {
        LOGGER.warn("异常信息为：{}", e);
        return new ApiResponse(BasicErrorCodeEnum.MOTAN_SERVICE_ERROR);
    }
    
    
//
//
//    /**
//     * 用户异常的捕获
//     *
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(value = UserException.class)
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    public ApiResponse UserErrorHandler(UserException e) {
//        LOGGER.warn("异常信息为：{}", e);
//        return new ApiResponse(e.getErrorId(), e.getMessage());
//    }
//
//    /**
//     * get请求参数的验证
//     *
//     * @return
//     */
//    @ExceptionHandler(value = MissingServletRequestParameterException.class)
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    public ApiResponse errorResponse(MissingServletRequestParameterException e) {
//        LOGGER.warn("异常信息为：{}", e);
//        return new ApiResponse(BasicErrorCodeEnum.PARAM_RESOLVE_ERROR.getCode(), "参数解析异常");
//    }
//
//
//
//
//
//
//    @ExceptionHandler(value = Exception.class)
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    public ApiResponse errorResponse(HttpServletRequest request, Exception e) {
//        LOGGER.error("异常uri：{}.", request.getRequestURI(), e);
//        return new ApiResponse(BasicErrorCodeEnum.UNKNOW_ERROR);
//    }
}