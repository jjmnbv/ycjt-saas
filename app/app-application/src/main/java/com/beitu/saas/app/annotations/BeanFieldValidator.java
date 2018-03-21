package com.beitu.saas.app.annotations;






import com.beitu.saas.common.enums.ValidateFieldEnum;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author poyangchen
 *         实体属性校验注解
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = BeanFieldValidatorImpl.class)
public @interface BeanFieldValidator {

    ValidateFieldEnum type();

    String message() default "field verification failed";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}