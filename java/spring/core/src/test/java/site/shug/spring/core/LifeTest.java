package site.shug.spring.core;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import site.shug.spring.autowire.AutoWire1;

public class LifeTest {
    @Test
    void testLift() {
        ApplicationContext context = new ClassPathXmlApplicationContext("life.xml");
    }
}
