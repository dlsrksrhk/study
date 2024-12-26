package sweet.dh.studyhard.corp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sweet.dh.studyhard.corp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}