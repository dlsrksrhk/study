package sweet.dh.studyhard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sweet.dh.studyhard.entity.Department;
import sweet.dh.studyhard.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}