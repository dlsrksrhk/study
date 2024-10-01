package sweet.dh.studyhard.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sweet.dh.studyhard.entity.Department;
import sweet.dh.studyhard.entity.User;
import sweet.dh.studyhard.repository.DepartmentRepository;
import sweet.dh.studyhard.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    @Cacheable(value = "userCache", key = "'allUsers'")
    public List<User> getAllUsers() {
        var users = userRepository.findAll();
        System.out.println("getAllUsers() - " + users);
        return users;
    }

    @Cacheable(value = "userCache", key = "#id")
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @CachePut(value = "userCache", key = "#user.id")
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @CacheEvict(value = "userCache", key = "#id")
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}