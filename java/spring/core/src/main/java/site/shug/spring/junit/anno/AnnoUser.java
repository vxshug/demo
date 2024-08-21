package site.shug.spring.junit.anno;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class AnnoUser {
    @Value("AnnoUser")
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AnnoUser{" +
                "name='" + name + '\'' +
                '}';
    }
}
