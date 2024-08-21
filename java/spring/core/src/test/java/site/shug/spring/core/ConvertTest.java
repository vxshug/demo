package site.shug.spring.core;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import site.shug.spring.convert.*;
import site.shug.spring.resources.ByteArrayResourceBean;

public class ConvertTest {
    /**
     * {@code PropertyEditor}将String转换成User
     * 使用场景：主要用于通过 Spring 的 XML 配置文件或注解注入字符串类型的属性时，需要将这些字符串转换为特定的复杂对象类型。
     */
    @Test
    void testPropertyEditor() {
        ApplicationContext context = new AnnotationConfigApplicationContext(PropertyEditorRegister.class, RequirePropertyEditorUser.class);
        RequirePropertyEditorUser bean = context.getBean(RequirePropertyEditorUser.class);
        System.out.println(bean);
    }
    /**
     * {@code Formatter}将String转换成User,
     * Formatter 是 Spring 3.x 中引入的一种机制，旨在处理字符串与复杂对象之间的双向转换，特别适用于需要格式化和解析的场景。
     * 使用场景：主要用于国际化、多语言支持场景中的数据格式化，比如将字符串转换为日期对象，或将数值转换为特定的格式。通常与 Spring MVC 一起使用，用于处理表单数据和路径变量。
     */
    @Test
    void testFormatter() {
        ApplicationContext context = new AnnotationConfigApplicationContext(FormatterRegister.class, RequireFormatterUser.class);
        RequireFormatterUser bean = context.getBean(RequireFormatterUser.class);
        System.out.println(bean);
    }
    /**
     * Formatter 是 Spring 3.x 中引入的一种机制，旨在处理字符串与复杂对象之间的双向转换，特别适用于需要格式化和解析的场景。
     * 使用场景：适用于在 Spring 中需要通用类型转换的地方，如注入属性、处理表单数据、以及 REST API 请求参数的转换。
     */
    @Test
    void testConverter() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConverterRegister.class, User.class, RequireConverterUser.class);
        RequireConverterUser bean = context.getBean(RequireConverterUser.class);
        System.out.println(bean);
    }
}
