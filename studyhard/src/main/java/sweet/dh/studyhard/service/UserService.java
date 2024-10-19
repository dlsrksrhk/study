package sweet.dh.studyhard.service;

import sweet.dh.studyhard.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User saveUser(User user);
    void deleteUser(Long id);
}
