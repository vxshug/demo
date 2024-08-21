package site.shug.spring.core;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import site.shug.spring.aop.anno.AnnoAopConfig;
import site.shug.spring.aop.anno.AnnoCalculator;
import site.shug.spring.aop.xml.XmlCalculator;

public class AopTest {

    AnnoCalculator getAnnoCalculator() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AnnoAopConfig.class);
        return context.getBean(AnnoCalculator.class);
    }

    @Test
    void testAdd() {
        AnnoCalculator calculator = getAnnoCalculator();
        calculator.add(1, 2);
    }
    @Test
    void testSubtract() {
        AnnoCalculator calculator = getAnnoCalculator();
        calculator.subtract(3, 2);
    }
    @Test
    void testAfterThrowing() throws Throwable {
        AnnoCalculator calculator = getAnnoCalculator();
        calculator.multiply(3, 2);
    }
    @Test
    void testAround() {
        AnnoCalculator calculator = getAnnoCalculator();
        calculator.divide(1, 0);
    }

    @Test
    void testXml() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:aop.xml");
        XmlCalculator calculator = context.getBean(XmlCalculator.class);
        calculator.add(1, 2);
        calculator.divide(1, 0);
    }
}
