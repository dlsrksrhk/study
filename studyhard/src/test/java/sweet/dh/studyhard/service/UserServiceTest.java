package sweet.dh.studyhard.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실제 DB 사용 시 필요
@Rollback(false) // 테스트 후에도 데이터가 남도록 설정
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private CacheUtil cacheUtil;

    private String CACHE_NAME = "userCache";

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
        cacheUtil.printCacheContents(CACHE_NAME);

        List<User> allUsersFromCache = userService.getAllUsers();
        System.out.println("allUsersFromCache - " + allUsersFromCache);
    }

    @Test
    public void testGetAllUsers() {
        // When
        List<User> users = userService.getAllUsers();

        // Then
        assertNotNull(users);
        assertFalse(users.isEmpty()); // 데이터가 있는지 확인
    }
}