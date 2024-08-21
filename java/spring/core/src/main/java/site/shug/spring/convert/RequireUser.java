package site.shug.spring.convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Component
@Configurable
public class RequireUser {
    private AdvancedUser user;
    public AdvancedUser getUser() {
        return user;
    }
    @Autowired
    public void setUser(AdvancedUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "RequireUser{" +
                "user=" + user +
                '}';
    }
}
