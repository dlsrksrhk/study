package sweet.dh.studyhard.corp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import sweet.dh.studyhard.corp.entity.Department;
import sweet.dh.studyhard.corp.entity.User;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class DepartmentServiceTest {
    @Autowired
    private DepartmentService departmentService;

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @Test
    public void addDepartmentToYUserTest() {
        Department department = new Department();
        department.setDeptName("IT");
        departmentService.saveDepartment(department);

        User user = new User();
        user.setUserName("amir");
        user.addDepartment(department);

        userService.saveUser(user);
    }

}