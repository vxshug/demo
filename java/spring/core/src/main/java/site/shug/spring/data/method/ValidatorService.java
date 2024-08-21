package site.shug.spring.data.method;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import site.shug.spring.data.User;

@Service
@Validated
public class ValidatorService {
    public String testUser(@NotNull @Valid User user) {
        return user.getName();
    }
    public String testCustomUser(@NotNull @Valid CustomUser user) {
        return user.getName();
    }
}
