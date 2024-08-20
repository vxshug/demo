package site.shug.spring.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.propertyeditors.ByteArrayPropertyEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import site.shug.spring.data.UserBean;

public class DataTest {
    @Test
    void testValidator() {
        ApplicationContext context = new AnnotationConfigApplicationContext("site.shug.spring.data");
        context.getBean(UserBean.class);
    }

    /**
     * BeanWrapper提供了设置和获取属性值（单独或批量）、获取属性描述符以及查询属性以确定它们是否可读或可写的功能。
     */
    @Test
    void testBeanWrapper() {
        BeanWrapper user = new BeanWrapperImpl(new UserBean());
        user.setPropertyValue("name", "shug");
        user.setPropertyValue("age", "18");
        System.out.println(user.getWrappedInstance());
    }

    /**
     * Spring 使用PropertyEditor的概念来实现Object和String之间的转换。
     */
    @Test
    void testByteArrayPropertyEditor() {
        ByteArrayPropertyEditor propertyEditor = new ByteArrayPropertyEditor();
        propertyEditor.setAsText("123");
        System.out.println(propertyEditor.getValue());
    }
}
