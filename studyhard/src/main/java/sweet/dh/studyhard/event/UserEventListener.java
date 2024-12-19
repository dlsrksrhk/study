package sweet.dh.studyhard.event;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import sweet.dh.studyhard.entity.User;
import sweet.dh.studyhard.service.UserService;

@Component
public class UserEventListener {

    private final UserService userService;

    public UserEventListener(@Qualifier("userServiceImpl") UserService userService) {
        this.userService = userService;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener
    public void handleUserCreatedEvent(UserCreatedEvent event) {
        User user = userService.getUserById(event.getUserId());
        user.setAge(30L);
    }
}