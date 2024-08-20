package site.shug.spring.core;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import site.shug.spring.property.AnnoAllProperty;
import site.shug.spring.property.AnnoProperty;
import site.shug.spring.property.EnvironmentProperties;
import site.shug.spring.property.XmlProperty;

public class PropertyTest {
    @Test
    void xmlPropertyTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("property.xml");
        XmlProperty b1 = context.getBean("xmlProperty", XmlProperty.class);
        System.out.println(b1);
    }

    @Test
    void annoPropertyTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AnnoProperty.class);
        AnnoProperty b1 = context.getBean(AnnoProperty.class);
        System.out.println(b1);
    }

    @Test
    void annoAllPropertyTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AnnoAllProperty.class);
        AnnoAllProperty b1 = context.getBean(AnnoAllProperty.class);
        System.out.println(b1);
    }

    @Test
    void annoEnvPropertyTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(EnvironmentProperties.class);
    }
}
