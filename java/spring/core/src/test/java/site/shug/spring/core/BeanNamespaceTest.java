package site.shug.spring.core;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanNamespaceTest {
    /**
     * destroy-method定义Bean释放时调用的方法
     * init-method定义Bean初始化时调用的方法(构造参数注入后)
     */
    @Test
    void initAndDestroy() {
        ApplicationContext context = new ClassPathXmlApplicationContext("namespacep.xml");
        BeanExampleDep b1 = context.getBean(BeanExampleDep.class);
        System.out.println(b1);
    }

    /**
     * p名称空间调用set方法注入
     */
    @Test
    void pNamespace() {
        ApplicationContext context = new ClassPathXmlApplicationContext("namespacep.xml");
        BeanExample b1 = context.getBean(BeanExample.class);
        System.out.println(b1);
    }

    /**
     * c名称空间调用构造方法注入
     */
    @Test
    void cNamespace() {
        ApplicationContext context = new ClassPathXmlApplicationContext("namespacec.xml");
        BeanExample b1 = context.getBean(BeanExample.class);
        System.out.println(b1);
    }

    /**
     * 依赖项, 先创建依赖项
     */
    @Test
    void depends() {
        ApplicationContext context = new ClassPathXmlApplicationContext("depends-on.xml");
    }

    /**
     * 需要时才创建
     */
    @Test
    void lazy() {
        ApplicationContext context = new ClassPathXmlApplicationContext("lazy.xml");
    }

    /**
     * 所有bean都需要时才创建
     */
    @Test
    void lazyAll() {
        ApplicationContext context = new ClassPathXmlApplicationContext("lazy-all.xml");
    }
}
