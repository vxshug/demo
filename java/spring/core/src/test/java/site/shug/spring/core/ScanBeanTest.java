package site.shug.spring.core;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ScanBeanTest {
    @Test
    public void testXmlScan() {
        ApplicationContext context = new ClassPathXmlApplicationContext("scan.xml");
    }

    @Test
    public void testAnnotationScan() {
        ApplicationContext context = new AnnotationConfigApplicationContext("site.shug.spring.annotations.beans");
    }
}
