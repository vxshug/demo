package site.shug.spring.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import site.shug.spring.common.JdbcConfig;
import site.shug.spring.common.dao.User;
import site.shug.spring.common.repository.UserRepository;

@SpringJUnitConfig(classes = JdbcConfig.class)
public class CommonDataTest {
    @Autowired
    UserRepository repository;
    @Test
    public void testDb() {
        User user = new User();
        user.setAge(12);
        user.setName("shug");
        repository.save(user);
        repository.findAll().forEach(System.out::println);
    }
}
