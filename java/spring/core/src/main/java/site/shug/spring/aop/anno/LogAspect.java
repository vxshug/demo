package site.shug.spring.aop.anno;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 切面类
 * 切入点表达式
 * execution(public int site.shug.spring.aop.Calculator.add(int, int))
 * 可以使用*作为通配符
 * execution(public int site.shug.spring.aop.Calculator.add(..))
 * 任意参数
 */
@Aspect
@Component
public class LogAspect {
    /**
     * 前置通知, 在切入点前面调用
     */
    @Before("execution(public int site.shug.spring.aop.anno.AnnoCalculatorImpl.add(..))")
    public void before() {
        System.out.println("before");
    }

    @Before("execution(public int site.shug.spring.aop.anno.AnnoCalculatorImpl.subtract(..))")
    public void beforeWithArgs(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        System.out.println("before with args method name: " + name);
    }

    /**
     * 后置通知, 在切入点后面调用
     */
    @After("execution(public int site.shug.spring.aop.anno.AnnoCalculatorImpl.add(..))")
    public void after() {
        System.out.println("after");
    }

    /**
     * 后置通知, 在切入点后面调用
     */
    @After("execution(public int site.shug.spring.aop.anno.AnnoCalculatorImpl.subtract(..))")
    public void after(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        System.out.println("after with args method name: " + name);
    }

    /**
     * 后置返回通知, 函数返回后调用
     */
    @AfterReturning("execution(public int site.shug.spring.aop.anno.AnnoCalculatorImpl.add(..))")
    public void afterReturning(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        System.out.println("afterReturning with args method name: " + name);
    }

    /**
     * 后置返回通知, 函数返回后调用, 获取函数的返回值
     */
    @AfterReturning(value = "execution(public int site.shug.spring.aop.anno.AnnoCalculatorImpl.subtract(..))", returning = "result")
    public void afterReturningMut(JoinPoint joinPoint, Object result) {
        String name = joinPoint.getSignature().getName();
        System.out.println("afterReturning with args method name: " + name + " result: " + result);
    }

    /**
     * 后置异常通知, 函数出现异常才会触发
     */
    @AfterThrowing(value = "execution(public int site.shug.spring.aop.anno.AnnoCalculatorImpl.add(..))", throwing = "ex")
    public void afterThrowingAdd(JoinPoint joinPoint, Throwable ex) {
        String name = joinPoint.getSignature().getName();
        System.out.println("afterThrowing with args method name: " + name + " ex: " + ex);
    }
    /**
     * 后置异常通知, 函数出现异常才会触发
     */
    @AfterThrowing(value = "execution(public int site.shug.spring.aop.anno.AnnoCalculatorImpl.multiply(..))", throwing = "ex")
    public void afterThrowingMultiply(JoinPoint joinPoint, Throwable ex) {
        String name = joinPoint.getSignature().getName();
        System.out.println("afterThrowing with args method name: " + name + " ex: " + ex);
    }

    /**
     * 环绕通知
     */
    @Around(value = "execution(public int site.shug.spring.aop.anno.AnnoCalculatorImpl.divide(..))")
    public Object around(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Object object = 1;
        try {
            System.out.println("around 目标方法调用前");
            object = joinPoint.proceed(args);
            System.out.println("around 目标方法调用后");
        } catch (Throwable e) {
            System.out.println("around 目标方法发送异常");
        } finally {
            System.out.println("around finally");
        }
        System.out.println("afterThrowing with args method name: " + methodName + " ex: " + args);
        return object;
    }
}
