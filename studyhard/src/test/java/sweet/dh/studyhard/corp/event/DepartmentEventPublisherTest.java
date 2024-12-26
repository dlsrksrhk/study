package sweet.dh.studyhard.corp.event;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sweet.dh.studyhard.corp.event.DepartmentEventPublisher;

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