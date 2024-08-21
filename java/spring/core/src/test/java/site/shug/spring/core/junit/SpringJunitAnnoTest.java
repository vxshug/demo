package site.shug.spring.core.junit;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import site.shug.spring.junit.anno.AnnoUser;
import site.shug.spring.junit.xml.XmlUser;

/**
 * 根据配置类创建Spring容器
 */
@SpringJUnitConfig(classes = AnnoUser.class)
public class SpringJunitAnnoTest {
    @Resource
    private AnnoUser annoUser;
    @Test
    void testXmlUser() {
        System.out.println(annoUser);
    }
}
