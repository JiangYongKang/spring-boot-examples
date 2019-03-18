package com.vincent.example.aspect;

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
 * Comment: AOP（Aspect-OrientedProgramming，面向方面编程），可以说是OOP（Object-Oriented Programing，面向对象编程）的补充和完善。
 * 将影响了多个类的公共行为封装到一个可重用模块，并将其名为 "Aspect"，即切面。
 * <p>
 * 使用场景:
 * 01. Authentication 权限
 * 02. Caching 缓存
 * 03. Context passing 内容传递
 * 04. Error handling 错误处理
 * 05. Lazy loading 懒加载
 * 06. Debugging 调试
 * 07. logging、tracing、profiling、monitoring 记录跟踪、优化、校准
 * 08. Performance optimization 性能优化
 * 09. Persistence 持久化
 * 10. Resource pooling资源池
 * 11. Synchronization 同步
 * 12. Transactions 事务
 * <p>
 * AOP 相关概念:
 * 01. Aspect（切面）: 一个关注点的模块化，这个关注点实现可能另外横切多个对象。事务管理是 J2EE 应用中一个很好的横切关注点例子。切面用 Spring 的 Advisor 或拦截器实现。
 * 02. JoinPoint（连接点）: 程序执行过程中明确的点，如方法的调用或特定的异常被抛出。
 * 03. Advice（通知）: 在特定的连接点，AOP 框架执行的动作。各种类型的通知包括 "Before"、"After"、"AfterReturning"、"AfterThrowing"、"Around" 通知。
 * 许多 AOP 框架包括 Spring 都是以拦截器做通知模型，维护一个 "围绕" 连接点的拦截器链。Spring 中定义了四个 advice: BeforeAdvice、AfterAdvice、ThrowAdvice 和 DynamicIntroductionAdvice
 * 04. Pointcut（切入点）: 指定一个通知将被引发的一系列连接点的集合。AOP 框架必须允许开发者指定切入点：例如，使用正则表达式。Spring定义了Pointcut 接口，
 * 用来组合 MethodMatcher 和 ClassFilter，可以通过名字很清楚的理解，MethodMatcher 是用来检查目标类的方法是否可以被应用此通知，而 ClassFilter 是用来检查 Pointcut 是否应该应用到目标类上
 * 05. Introduction（引入）: 添加方法或字段到被通知的类。 Spring 允许引入新的接口到任何被通知的对象。例如，你可以使用一个引入使任何对象实现 IsModified 接口，来简化缓存。
 * Spring 中要使用 Introduction，可有通过 DelegatingIntroductionInterceptor 来实现通知，通过 DefaultIntroductionAdvisor 来配置 Advice 和代理类要实现的接口
 * 06. Target Object（目标对象）: 包含连接点的对象。也被称作被通知或被代理对象。
 * 07. AOP Proxy（AOP 代理）: AOP 框架创建的对象，包含通知。 在 Spring 中，AOP 代理可以是 JDK 动态代理或者 CGLIB 代理。
 * 08. Weaving（织入）: 组装方面来创建一个被通知对象。这可以在编译时完成（例如使用 AspectJ 编译器），也可以在运行时完成。
 * Spring 和其他纯 Java AOP 框架一样，在运行时完成织入。
 */
@Aspect
@Component
public class ControllerAspect {

    private static Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    /**
     * 切入点: 通过 AspectJ 切入点语法匹配
     */
    @Pointcut(value = "execution(* com.vincent.example.controller.*.*(..))")
    public void controllerPointcut() {
    }

    /**
     * 切入点: 绝对包路径指定类下的任意方法
     */
    @Pointcut(value = "execution(* com.vincent.example.controller.BaseController.*(..))")
    public void baseControllerPointcut() {
    }

    /**
     * 切入点：@annotation 通过注解切入
     */
    @Pointcut(value = "@annotation(org.springframework.web.bind.annotation.ResponseBody)")
    public void responseBodyPointcut() {
    }

    /**
     * 前置通知（目标方法执行前执行）
     * 切入点可以通过 "&&"、"||"、"!" 逻辑表达式灵活组合
     * @param point
     */
    @Before(value = "controllerPointcut() && !baseControllerPointcut()")
    public void doBefore(JoinPoint point) {
        logger.info("@Before: 连接点对象: " + point.getTarget());
        logger.info("@Before: 连接点方法: " + AspectUtils.displayJoinPointMethod(point));
        logger.info("@Before: 连接点输入参数: " + Arrays.toString(point.getArgs()));
    }

    /**
     * 最终通知（无论是否抛出异常，该通知都会执行）
     * @param point
     */
    @After(value = "controllerPointcut()")
    public void doAfter(JoinPoint point) {
        logger.info("@After: 连接点返回对象: ");
    }

    /**
     * 后置通知（方法正常执行完毕后执行）
     * returning = "result" 为参数列表中的 "Object result"
     * "Object result" 为目标方法的返回值
     * @param point
     * @param result
     */
    @AfterReturning(value = "controllerPointcut()", returning = "result")
    public void doAfterReturning(JoinPoint point, Object result) {
        logger.info("@AfterReturning: 连接点返回对象: " + result.toString());
    }

    /**
     * 异常抛出通知（在抛出异常后通知）
     * throwing = "e" 为 参数列表中的 "Exception e"
     * 而目标方法抛出的异常就是它
     * @param point
     * @param e
     */
    @AfterThrowing(value = "controllerPointcut()", throwing = "e")
    public void doAfterThrowing(JoinPoint point, Exception e) {
        logger.error("@AfterThrowing: 连接点抛出异常: " + e.getMessage());
    }

    /**
     * 环绕通知（最高优先级）
     * 在目标方法执行前后都会触发
     * 调用 point.proceed() 方法执行目标方法，目标方法抛出异常，这里也同样会抛出异常
     * 这里的异常如果抛给上一级，则会自动进入 @AfterThrowing 方法
     * 记住: 是抛出异常，而不是被 try catch 吃掉
     * @param point
     * @return
     */
    @Around(value = "controllerPointcut()")
    public Object doAround(ProceedingJoinPoint point) {
        try {
            return point.proceed();
        } catch (Throwable throwable) {
            AspectUtils.printlnStackTraceElement(throwable.getStackTrace());
            return null;
        }
    }
}
