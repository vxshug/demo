package site.shug.spring.core;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import site.shug.spring.i18n.I18nConfig;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 国际化(i8n)
 */
public class I18nTest {
    /**
     * 配置文件规则basename_language_country.properties, basename必要的名称, 如示例中的messages, language语言, 如zh, en, country国家CN, GB
     */
    @Test
    void testJavaNative() {
        ResourceBundle bundle1 = ResourceBundle.getBundle("messages", new Locale("zh", "CN"));
        String tipZh = bundle1.getString("tip");
        System.out.println(tipZh);

        ResourceBundle bundle2 = ResourceBundle.getBundle("messages", new Locale("en", "US"));
        String tipEn = bundle2.getString("tip");
        System.out.println(tipEn);
    }
    /**
     * Spring是通过@{code MessageSource}接口实现国际化, 实现类
     * {@code ResourceBundleMessageSource} 基于Java的{@code ResourceBundle}实现
     * {@code ReloadableResourceBundleMessageSource} 与{@code ResourceBundleMessageSource}类似, 但是多了定时刷新
     * {@code StaticMessageSource} 以编程方式提供国际化信息
     */
    @Test
    void testSpring() {
        ApplicationContext context = new AnnotationConfigApplicationContext(I18nConfig.class);
        String[] args = new String[] {"shug0", "shug1"};
        String message1 = context.getMessage("hello", args, new Locale("en", "US"));
        System.out.println(message1);
        String message2 = context.getMessage("hello", args, new Locale("zh", "CN"));
        System.out.println(message2);
    }
}
