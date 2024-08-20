package site.shug.spring.annotations.beans;

import org.springframework.stereotype.Component;

/**
 * Component用于类上, 将类的实例注册到Spring容器中
 * 可以使用`jakarta.inject.Named`或`jakarta.annotation.ManagedBean`代替
 */
@Component
public class ComponentBean {
    public ComponentBean() {
        System.out.println("ComponentBean");
    }
}
