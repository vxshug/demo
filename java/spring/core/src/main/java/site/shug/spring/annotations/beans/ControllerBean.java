package site.shug.spring.annotations.beans;

import org.springframework.stereotype.Controller;

/**
 * Controller用于类上, 将类的实例注册到Spring容器中, 用于表示层
 */
@Controller
public class ControllerBean {
    public ControllerBean() {
        System.out.println("ControllerBean");
    }
}
