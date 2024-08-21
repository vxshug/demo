package site.shug.spring.core;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import site.shug.spring.resources.*;

public class ResourceTest {
    @Test
    void testUrl() {
        ApplicationContext context = new AnnotationConfigApplicationContext(UrlResourceBean.class);
        UrlResourceBean bean = context.getBean(UrlResourceBean.class);
        System.out.println(bean);
    }
    @Test
    void testClassPath() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ClassPathResourceBean.class);
        ClassPathResourceBean bean = context.getBean(ClassPathResourceBean.class);
        System.out.println(bean);
    }
    @Test
    void testFileSystem() {
        ApplicationContext context = new AnnotationConfigApplicationContext(FileSystemResourceBean.class);
        FileSystemResourceBean bean = context.getBean(FileSystemResourceBean.class);
        System.out.println(bean);
    }
    @Test
    void testPath() {
        ApplicationContext context = new AnnotationConfigApplicationContext(PathResourceBean.class);
        PathResourceBean bean = context.getBean(PathResourceBean.class);
        System.out.println(bean);
    }
    @Test
    void testInputStream() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyFormattingConversionServiceFactoryBean.class, InputStreamResourceBean.class);
        InputStreamResourceBean bean = context.getBean(InputStreamResourceBean.class);
        System.out.println(bean);
    }
    @Test
    void testByteArray() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyFormattingConversionServiceFactoryBean.class, ByteArrayResourceBean.class);
        ByteArrayResourceBean bean = context.getBean(ByteArrayResourceBean.class);
        System.out.println(bean);
    }
}
