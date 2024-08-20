package site.shug.spring.annotations.beans;

import org.springframework.stereotype.Repository;

/**
 * Repository用于类上, 将类的实例注册到Spring容器中, 用于持久层
 */
@Repository
public class RepositoryBean {
    public RepositoryBean() {
        System.out.println("RepositoryBean");
    }
}
