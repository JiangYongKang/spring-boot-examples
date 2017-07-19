package com.vincent.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/**
 * Created by IDEA.
 * User: vincent
 * Date: 2017/7/19
 * Comment:
 */
@Aspect
@Component
public class ControllerAspect {

    private static Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    @Pointcut(value = "execution(* com.vincent.controller.*.*(..))")
    public void controllerPointcut() {}

    @Pointcut(value = "@annotation(org.springframework.web.bind.annotation.ResponseBody)")
    public void responseBodyPointcut() {}

    @Before(value = "controllerPointcut()")
    public void doBefore(JoinPoint point) {
        logger.info("@Before: 目标对象: " + point.getTarget());
        logger.info("@Before: 目标方法: " + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName() + "()");
        logger.info("@Before: 目标参数: " + Arrays.toString(point.getArgs()));
    }

    @After(value = "controllerPointcut()")
    public void doAfter(JoinPoint point) {

    }

    @AfterReturning(value = "controllerPointcut()", returning = "result")
    public void doAfterReturning(JoinPoint point, Object result) {

    }

    @AfterThrowing(value = "controllerPointcut()", throwing = "e")
    public void doAfterThrowing(JoinPoint point, Exception e) {

    }

    @Around(value = "controllerPointcut()")
    public Object doAround(ProceedingJoinPoint point) {
        try {
            return point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    private static String display(JoinPoint point) {
        return null;
    }
}
