package com.beitu.saas.rest.aop;

import com.fqgj.common.api.ParamsObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ParamsValidateAop {

    //controller层切入点
    @Pointcut("@annotation(com.fqgj.common.api.annotations.ParamsValidate)")
    public void controllerAspect() {
        System.out.println("========serviceAspect===========");
    }

    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                System.out.println(arg.getClass());
                if (ParamsObject.class.isAssignableFrom(arg.getClass())) {
                    ParamsObject p = (ParamsObject) arg;
                    p.validate();
                }
            }
        }
    }

}