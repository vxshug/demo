package site.shug.spring.annotations.beans;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Configurable 仅支持依赖注入：它不会自动将对象注册为 Spring bean。
 */
@Configurable
@Component
public class ConfigurationBean {
    public ConfigurationBean() {
        System.out.println("ConfigurationBean");
    }
    public static class AnnoBean {
        public AnnoBean() {
            System.out.println("AnnoBean");
        }
    }
    @Bean
    public AnnoBean annoBean() {
        return new AnnoBean();
    }
}
