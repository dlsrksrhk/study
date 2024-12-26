package sweet.dh.studyhard.corp.service;

import sweet.dh.studyhard.corp.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User saveUser(User user);
    void deleteUser(Long id);
}
