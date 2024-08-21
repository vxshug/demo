package site.shug.spring.convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.stereotype.Component;

@Component
@Configurable
public class RequireConverterUser {
    private AdvancedUser user;
    public AdvancedUser getUser() {
        return user;
    }
    public void setUser(AdvancedUser user) {
        this.user = user;
    }

    /**
     *
     * @param user 原始的User
     * @param converter FormattingConversionService 通过注册的converter将User转换成AdvancedUser
     */
    public RequireConverterUser(@Autowired User user, @Autowired FormattingConversionService converter) {
        this.user = converter.convert(user, AdvancedUser.class);
    }

    @Override
    public String toString() {
        return "RequireConverterUser{" +
                "user=" + user +
                '}';
    }
}
