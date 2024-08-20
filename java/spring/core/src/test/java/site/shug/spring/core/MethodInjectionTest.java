package site.shug.spring.core;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import site.shug.spring.autowire.AutoWire1;
import site.shug.spring.method.Replacer;
import site.shug.spring.method.SingleBean;
import site.shug.spring.method.SingleBeanLook;

public class MethodInjectionTest {
    @Test
    void testApplicationContextAware() {
        ApplicationContext context = new ClassPathXmlApplicationContext("method.xml");
        SingleBean b1 = context.getBean(SingleBean.class);
        b1.getNoSingleBean();
        b1.getNoSingleBean();
    }

    /**
     * Look Method注意
     * - 为了使这种动态子类化工作，Spring bean 容器子类化的类不能是final ，并且要重写的方法也不能是final 。
     * - 对具有abstract方法的类进行单元测试需要您自己对该类进行子类化并提供abstract方法的存根实现。
     * - 组件扫描也需要具体的方法，这需要选取具体的类。
     * - 另一个关键限制是查找方法不能与工厂方法一起使用，特别是不能与配置类中的@Bean方法一起使用，因为在这种情况下，容器不负责创建实例，因此无法创建运行时生成的实例。动态子类。
     */
    @Test
    void testLookMethod() {
        ApplicationContext context = new ClassPathXmlApplicationContext("method.xml");
        SingleBeanLook b1 = context.getBean(SingleBeanLook.class);
        b1.getNoSingleBean();
        b1.getNoSingleBean();
    }

    /**
     * 替换托管 bean 中的任意方法。
     */
    @Test
    void testReplaceMethod() {
        ApplicationContext context = new ClassPathXmlApplicationContext("method.xml");
        Replacer b1 = context.getBean(Replacer.class);
        b1.sayHello("1");
        b1.sayHello("2");
    }
}
