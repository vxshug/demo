package site.shug.spring.core;


import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.propertyeditors.ByteArrayPropertyEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import site.shug.spring.data.User;
import site.shug.spring.data.UserBean;
import site.shug.spring.data.ValidatorBean;
import site.shug.spring.data.ValidatorConfig;
import site.shug.spring.data.method.CustomUser;
import site.shug.spring.data.method.MethodValidatorConfig;
import site.shug.spring.data.method.ValidatorService;

import java.util.Set;

public class DataTest {
    /**
     * 使用DataBinder完成数据校验
     */
    @Test
    void testValidator() {
        UserBean bean = new UserBean();
        bean.setAge(18);
        DataBinder dataBinder = new DataBinder(bean);
        dataBinder.setValidator(new ValidatorBean());
        dataBinder.validate();
        BindingResult result = dataBinder.getBindingResult();
        System.out.println(result);
    }

    /**
     * 使用jakarta.validation.Validator完成数据校验
     */
    @Test
    void testValidatorAnnoNative() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ValidatorConfig.class);
        jakarta.validation.Validator validator = context.getBean(jakarta.validation.Validator.class);
        Set<ConstraintViolation<User>> validate = validator.validate(new User());
        System.out.println(validate);
    }
    /**
     * 使用org.springframework.validation.Validator完成数据校验
     */
    @Test
    void testValidatorAnnoSpring() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ValidatorConfig.class);
        org.springframework.validation.Validator validator = context.getBean(org.springframework.validation.Validator.class);
        User user = new User();
        BindException bindException = new BindException(user, user.getName());
        validator.validate(user, bindException);
        System.out.println(bindException);
    }
    /**
     * 使用MethodValidationPostProcessor配合{@code Validated} 和 {@code Valid}完成数据校验
     */
    @Test
    void testValidatorAnnoMethodSpring() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MethodValidatorConfig.class);
        ValidatorService service = context.getBean(ValidatorService.class);
        User user = new User();
        user.setAge(20);
        user.setName("hello");
        service.testUser(user);
    }

    /**
     * 自定义验证注解
     */
    @Test
    void testValidatorAnnoMethodCustom() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MethodValidatorConfig.class);
        ValidatorService service = context.getBean(ValidatorService.class);
        CustomUser user = new CustomUser();
        user.setAge(20);
        user.setName("hello shug");
        service.testCustomUser(user);
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
