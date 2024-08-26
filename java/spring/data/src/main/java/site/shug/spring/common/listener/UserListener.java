package site.shug.spring.common.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import site.shug.spring.common.dao.User;
import site.shug.spring.common.event.UserCreatedEvent;

@Component
public class UserListener {
    @EventListener
    public void handleUserCreatedEvent(UserCreatedEvent event) {
        User user = event.getUser();
        System.out.println("UserListener: " + user);
    }
}
