package site.shug.spring.convert;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Configurable
public class RequirePropertyEditorUser {
    @Value("shug;18")
    private User user;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "RequireUser{" +
                "user=" + user +
                '}';
    }
}
