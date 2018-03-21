package com.beitu.saas.app.annotations;


import com.beitu.saas.common.enums.ValidateFieldEnum;
import com.fqgj.common.utils.MobileUtil;
import com.fqgj.common.utils.PasswordUtil;
import com.fqgj.exception.common.ApplicationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author poyangchen
 *         实体属性校验实现
 */
@Component
public class BeanFieldValidatorImpl implements ConstraintValidator<BeanFieldValidator, Object> {

    private ValidateFieldEnum fieldEnum;

    @Override
    public void initialize(BeanFieldValidator constraintAnnotation) {
        this.fieldEnum = constraintAnnotation.type();
    }

    @Override
    public boolean isValid(Object field, ConstraintValidatorContext constraintValidatorContext) {

        if (fieldEnum == ValidateFieldEnum.MOBILE) {
            return checkMobile(field);
        }
        if (fieldEnum == ValidateFieldEnum.PASSWORD) {
            return checkPassword(field);
        }
        
        return false;
    }


    private boolean checkMobile(Object field) {

        String mobile = (String) field;
        if (StringUtils.isEmpty(mobile)) {
            throw new ApplicationException("手机号不能为空");
        }
        if (MobileUtil.isMobile(mobile)) {
            return true;
        }
        return false;
    }
    
    private boolean checkPassword(Object field) {
        
        String password = (String) field;
        if (StringUtils.isEmpty(password)) {
            throw new ApplicationException("密码不能为空");
        }
        if (PasswordUtil.isPasswordValidate(password)) {
            return true;
        }
        return false;
    }

}