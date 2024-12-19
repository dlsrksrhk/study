package sweet.dh.studyhard.event;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentEventPublisherTest {
    @Autowired
    private DepartmentEventPublisher departmentEventPublisher;

    @Test
    void testPublishDepartmentEvent() {
        Long deptId = 1L;
        String deptName = "IT";

        departmentEventPublisher.publishDepartmentEvent(deptId, deptName);
    }
}