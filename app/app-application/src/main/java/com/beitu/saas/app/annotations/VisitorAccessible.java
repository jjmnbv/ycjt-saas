package com.beitu.saas.app.annotations;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * A restful api annotated by this annotation can be accessible to the visitors.
 *
 * @author yujianfu
 */
@Inherited
@Retention(RUNTIME)
@Target({METHOD})
public @interface VisitorAccessible {
}
