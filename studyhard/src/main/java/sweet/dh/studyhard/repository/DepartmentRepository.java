package sweet.dh.studyhard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sweet.dh.studyhard.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
