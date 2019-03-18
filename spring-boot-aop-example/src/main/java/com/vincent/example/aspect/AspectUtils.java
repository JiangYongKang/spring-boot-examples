package com.vincent.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AspectUtils {

    private static Logger logger = LoggerFactory.getLogger(AspectUtils.class);

    /**
     * 获取目标对象的信息
     * @param point
     * @return
     */
    static String displayJoinPointMethod(JoinPoint point) {
        return point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName() + "()";
    }

    /**
     * 打印错误堆栈
     * @param stackTraceElements
     */
    static void printlnStackTraceElement(StackTraceElement[] stackTraceElements) {
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            logger.error(stackTraceElement.toString());
        }
    }
}
