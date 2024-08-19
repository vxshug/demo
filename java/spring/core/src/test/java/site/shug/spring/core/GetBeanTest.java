package site.shug.spring.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GetBeanTest {

    // registerSingleton注册Bean
    @Test
    void getRegisterSingletonBeanByClass() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        context.refresh();
        ConfigurableListableBeanFactory f1 = context.getBeanFactory();
        BeanExample b1 = new BeanExample("registerSingleton", 10);
        f1.registerSingleton("bean1", b1);
        BeanExample b2 = context.getBean(BeanExample.class);
        System.out.println(b2);
    }

    // 根据Class查询Bean
    @Test
    void getBeanByClass() {
        ApplicationContext context = new ClassPathXmlApplicationContext("getbean.xml");
        BeanExample b1 = context.getBean(BeanExample.class);
        System.out.println(b1);
    }
    // 根据Bean名称和Class查询Bean
    @Test
    void getBeanByName() {
        ApplicationContext context = new ClassPathXmlApplicationContext("getbean.xml");
        BeanExample b1 = (BeanExample)context.getBean("beanExample0");
        System.out.println(b1);
    }
    // 根据Bean名称和Class查询Bean
    @Test
    void getBeanByNameAndClass() {
        ApplicationContext context = new ClassPathXmlApplicationContext("getbean.xml");
        BeanExample b1 = context.getBean("beanExample0", BeanExample.class);
        System.out.println(b1);
    }
    // 静态工厂方法创建的Bean
    @Test
    void getFactoryBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("getbean.xml");
        BeanExample b1 = context.getBean("beanExample1", BeanExample.class);
        System.out.println(b1);
    }
    // 静态工厂方法带参数创建的Bean
    @Test
    void getFactoryBean1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("getbean.xml");
        BeanExample b1 = context.getBean("beanExample11", BeanExample.class);
        System.out.println(b1);
    }

    // 工厂方法创建的Bean
    @Test
    void getFactory1Bean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("getbean.xml");
        BeanExample b1 = context.getBean("beanExample2", BeanExample.class);
        System.out.println(b1);
    }
    // 构造方法创建Bean
    @Test
    void getConstructorBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("getbean.xml");
        BeanExample b1 = context.getBean("beanExample3", BeanExample.class);
        System.out.println(b1);
    }
    // set方法创建Bean
    @Test
    void getSetterBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("getbean.xml");
        BeanExample b1 = context.getBean("beanExample4", BeanExample.class);
        System.out.println(b1);
    }
    // set方法创建嵌套Bean
    @Test
    void getSetter1Bean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("getbean.xml");
        BeanExample b1 = context.getBean("beanExample5", BeanExample.class);
        System.out.println(b1);
    }
}
