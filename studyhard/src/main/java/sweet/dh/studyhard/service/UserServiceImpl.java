package sweet.dh.studyhard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import sweet.dh.studyhard.entity.User;
import sweet.dh.studyhard.event.UserCreatedEvent;
import sweet.dh.studyhard.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Cacheable(value = "userCache", key = "'allUsers'")
    public List<User> getAllUsers() {
        var users = userRepository.findAll();
        System.out.println("getAllUsers() - " + users);
        return users;
    }

    @Override
    @Cacheable(value = "userCache", key = "#id")
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Caching(put = {@CachePut(value = "userCache", key = "#user.id")},
            evict = {@CacheEvict(value = "userCache", key = "'allUsers'")})
    public User saveUser(User user) {
        userRepository.save(user);
        eventPublisher.publishEvent(new UserCreatedEvent(user.getId(), user.getUserName()));
        return user;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "userCache", key = "'allUsers'"),
            @CacheEvict(value = "userCache", key = "#id")
    })
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}