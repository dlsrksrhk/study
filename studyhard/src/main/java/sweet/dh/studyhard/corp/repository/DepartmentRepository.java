package sweet.dh.studyhard.corp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sweet.dh.studyhard.corp.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
