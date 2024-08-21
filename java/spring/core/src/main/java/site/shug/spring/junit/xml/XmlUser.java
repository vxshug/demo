package site.shug.spring.junit.xml;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class XmlUser {
    @Value("XmlUser")
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "XmlUser{" +
                "name='" + name + '\'' +
                '}';
    }
}
