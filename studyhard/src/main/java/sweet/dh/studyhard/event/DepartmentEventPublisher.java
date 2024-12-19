package sweet.dh.studyhard.event;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DepartmentEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishDepartmentEvent(Long deptId, String deptName) {
        applicationEventPublisher.publishEvent(DepartmentEvent.of(this, deptId, deptName));
    }
}
