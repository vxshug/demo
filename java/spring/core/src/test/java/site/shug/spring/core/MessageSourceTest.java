package site.shug.spring.core;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Locale;

/**
 * 获取消息
 */
public class MessageSourceTest {
    @Test
    void testGetMessage() {
        ApplicationContext context = new ClassPathXmlApplicationContext("messageSource.xml");
        String message = context.getMessage("message", null, "Default", Locale.ENGLISH);
        System.out.println(message);
        String requiredMessage = context.getMessage("argument.required",
                new Object [] {"userDao"}, "Required", Locale.ENGLISH);
        System.out.println(requiredMessage);
    }
}
