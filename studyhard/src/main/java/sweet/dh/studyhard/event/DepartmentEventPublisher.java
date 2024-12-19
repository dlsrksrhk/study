package sweet.dh.studyhard.event;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class DepartmentEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishDepartmentEvent(Long deptId, String deptName) {
        log.info("Publishing Department Event: {}, {}", deptId, deptName);
        applicationEventPublisher.publishEvent(DepartmentEvent.of(this, deptId, deptName));
    }
}
