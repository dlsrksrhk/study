package sweet.dh.studyhard.event;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
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

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handleUserCreatedEvent(UserCreatedEvent event) {
        System.out.println("User created successfully!");
        System.out.println("User ID: " + event.getUserId());
        System.out.println("Username: " + event.getUserName());
        // do something with the event
        boolean isTransactionActive = TransactionSynchronizationManager.isActualTransactionActive();
        boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        String transactionName = TransactionSynchronizationManager.getCurrentTransactionName();

        System.out.println("Transaction active: " + isTransactionActive);
        System.out.println("Transaction read-only: " + isReadOnly);
        System.out.println("Transaction name: " + transactionName);
        User user = userService.getUserById(event.getUserId());
        user.setAge(30L);
//        throw new RuntimeException();
    }
}