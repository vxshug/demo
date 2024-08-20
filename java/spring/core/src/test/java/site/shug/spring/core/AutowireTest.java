package site.shug.spring.core;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import site.shug.spring.autowire.AutoWire1;

public class AutowireTest {
    /**
     * 通过自动byName自动装配Bean
     */
    @Test
    void autowireTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("autowiring.xml");
        AutoWire1 b1 = context.getBean("autoWire1", AutoWire1.class);
        System.out.println(b1);
    }
    /**
     * 通过自动byType自动装配Bean
     */
    @Test
    void autowireTestByType() {
        ApplicationContext context = new ClassPathXmlApplicationContext("autowiring-by-type.xml");
        AutoWire1 b1 = context.getBean("autoWire1", AutoWire1.class);
        System.out.println(b1);
    }

    /**
     * 通过自动constructor自动装配Bean, 与ByType类似, 但是是通过构造函数创建Bean
     */
    @Test
    void autowireTestConstructor() {
        ApplicationContext context = new ClassPathXmlApplicationContext("autowiring-constructor.xml");
        AutoWire1 b1 = context.getBean("autoWire1", AutoWire1.class);
        System.out.println(b1);
    }
    /**
     * autowire-candidate="false"关闭自动装配
     */
    @Test
    void autowireTestCandidate() {
        ApplicationContext context = new ClassPathXmlApplicationContext("autowire-candidate.xml");
        AutoWire1 b1 = context.getBean("autoWire1", AutoWire1.class);
        System.out.println(b1);
    }
}
