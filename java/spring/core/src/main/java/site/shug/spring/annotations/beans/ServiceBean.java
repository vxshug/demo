package site.shug.spring.annotations.beans;

import org.springframework.stereotype.Service;

/**
 * Service用于类上, 将类的实例注册到Spring容器中, 用于服务层
 */
@Service
public class ServiceBean {
    public ServiceBean() {
        System.out.println("ServiceBean");
    }
}
