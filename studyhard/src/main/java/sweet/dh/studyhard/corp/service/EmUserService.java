package sweet.dh.studyhard.corp.service;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sweet.dh.studyhard.corp.entity.User;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmUserService implements UserService {
    private final EntityManager entityManager;

    @Override
    @Cacheable(value = "userCache", key = "'allUsers'")
    public List<User> getAllUsers() {
        var users = entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
        System.out.println("getAllUsers() - " + users);
        return users;
    }

    @Override
    @Cacheable(value = "userCache", key = "#id")
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Caching(put = {@CachePut(value = "userCache", key = "#user.id")},
            evict = {@CacheEvict(value = "userCache", key = "'allUsers'")})
    public User saveUser(User user) {
        if (user.getId() == null) {
            entityManager.persist(user);
        } else {
            user = entityManager.merge(user);
        }
        return user;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "userCache", key = "'allUsers'"),
            @CacheEvict(value = "userCache", key = "#id")
    })
    public void deleteUser(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }
}