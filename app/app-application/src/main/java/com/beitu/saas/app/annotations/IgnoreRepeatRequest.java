package com.beitu.saas.app.annotations;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author xiaochong
 * @Create 2017/9/27 下午5:38
 * @Description
 */
@Inherited
@Retention(RUNTIME)
@Target({METHOD})
public @interface IgnoreRepeatRequest {
}
