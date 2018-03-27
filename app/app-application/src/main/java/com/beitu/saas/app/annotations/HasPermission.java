package com.beitu.saas.app.annotations;


import java.lang.annotation.*;

/**
 * @Author xiaochong
 * @Create 2017/9/27 下午5:38
 * @Description
 */
@Inherited
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HasPermission {

    String permissionKey() default "";
}
