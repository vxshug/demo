package site.shug.spring.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * PropertySources导入文件properties的值
 */
@Configuration
@PropertySource("classpath:properties.properties")
public class AnnoAllProperty {
    @Value("${user.name}")
    private String name;
    @Value("${user.age}")
    private int age;
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AnnoAllProperty{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
