package site.shug.spring.core;

import org.junit.jupiter.api.Test;
import site.shug.spring.aop.cal.Calculator;
import site.shug.spring.aop.cal.CalculatorImpl;
import site.shug.spring.aop.cal.CalculatorStaticProxy;
import site.shug.spring.aop.cal.ProxyFactory;

/**
 * 测试代理方式
 */
public class ProxyTest {
    /**
     * 测试静态代理
     */
    @Test
    void testStatic() {
        Calculator calculator = new CalculatorStaticProxy(new CalculatorImpl());
        System.out.println(calculator.add(1, 2));
    }

    /**
     * 测试动态代理
     */
    @Test
    void testProxy() {
        ProxyFactory factory = new ProxyFactory(new CalculatorImpl());
        Calculator calculator = (Calculator)factory.getProxy();
        System.out.println(calculator.add(1, 2));
    }
}
