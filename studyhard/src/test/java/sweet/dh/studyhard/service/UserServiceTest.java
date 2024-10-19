package sweet.dh.studyhard.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import sweet.dh.studyhard.entity.User;
import sweet.dh.studyhard.util.CacheUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserServiceTest {

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @Autowired
    private CacheUtil cacheUtil;

    private final String CACHE_NAME = "userCache";

    @Test
    public void testSaveUser() {
        // Given
        User newUser = new User();
        newUser.setUserName("Jane Doe");

        // When
        User savedUser = userService.saveUser(newUser);

        // Then
        assertNotNull(savedUser);
        assertEquals("Jane Doe", savedUser.getUserName());
    }

    @Test
    public void testCacheAfterSave() {
        List<User> allUsers = userService.getAllUsers();
        System.out.println("found in cache : " + cacheUtil.findCacheContents(CACHE_NAME));;

        List<User> allUsersFromCache = userService.getAllUsers();
        System.out.println("allUsersFromCache - " + allUsersFromCache);

        userService.deleteUser(1L);
        userService.getAllUsers();
    }

    @Test
    public void testGetAllUsers() {
        // When
        List<User> users = userService.getAllUsers();

        // Then
        assertNotNull(users);
        assertFalse(users.isEmpty()); // 데이터가 있는지 확인
    }

    @Test
    public void testDelete() {
        // Given
        User newUser = new User();
        newUser.setUserName("Jane Doe33");
        User savedUser = userService.saveUser(newUser);

        // When
        userService.deleteUser(savedUser.getId());

        // Then
        assertNull(userService.getUserById(savedUser.getId()));
    }
}