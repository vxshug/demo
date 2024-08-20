package site.shug.spring.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:properties.properties")
public class EnvironmentProperties {
    @Autowired
    Environment env;

    @Bean
    public AnnoProperty getAnnoProperty() {
        AnnoProperty property = new AnnoProperty();
        property.setName(env.getProperty("user.name"));
        property.setAge(env.getProperty("user.age", Integer.class));
        System.out.println(property);
        return property;
    }
}
