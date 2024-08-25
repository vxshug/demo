package site.shug.spring.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import site.shug.spring.common.JdbcConfig;
import site.shug.spring.common.dao.RecordUser;
import site.shug.spring.common.dao.User;
import site.shug.spring.common.repository.RecordUserRepository;
import site.shug.spring.common.repository.UserRepository;

@SpringJUnitConfig(classes = JdbcConfig.class)
public class CommonDataTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RecordUserRepository recordUserRepository;
    @Test
    public void testDb() {
        User user = new User(null, "shug", 12);
        userRepository.save(user);
        userRepository.save(user);
        userRepository.save(user);
        userRepository.save(user);
        userRepository.save(user);
        userRepository.save(user);
        System.out.println("saved user");
        userRepository.findByName("shug", Pageable.ofSize(2))
                .forEach(System.out::println);
//        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void testRecordUser() {
        RecordUser user = new RecordUser(null, "shug", "nick", 12);

        recordUserRepository.save(user);
        recordUserRepository.findAll().forEach(System.out::println);
    }
}
