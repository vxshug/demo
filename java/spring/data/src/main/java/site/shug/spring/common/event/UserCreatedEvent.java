package site.shug.spring.common.event;

import site.shug.spring.common.dao.User;

public class UserCreatedEvent {
    private final User user;

    public UserCreatedEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
