package site.shug.spring.core.junit;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import site.shug.spring.junit.xml.XmlUser;

/**
 * 自动使用junit.xml创建Spring容器
 */
@SpringJUnitConfig(locations = "classpath:junit.xml")
public class SpringJunitTest {
    @Resource
    private XmlUser xmlUser;
    @Test
    void testXmlUser() {
        System.out.println(xmlUser);
    }
}
