package sweet.dh.studyhard.event;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.TestPropertySource;
import sweet.dh.studyhard.entity.User;
import sweet.dh.studyhard.service.UserService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class UserEventListenerTest {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Test
    public void testUserCreatedEvent() {
        // Given
        User newUser = new User();
        newUser.setUserName("John Doe");

        // When
        User savedUser = userService.saveUser(newUser);

        // Then
        assertNotNull(savedUser);
        assertEquals("John Doe", savedUser.getUserName());

        // Verify that the event is published
        // This part can be enhanced by using a mock framework to verify the event publication
        // For simplicity, we are just printing a message in the event listener
    }
}