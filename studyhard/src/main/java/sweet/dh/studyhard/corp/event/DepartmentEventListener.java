package sweet.dh.studyhard.corp.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DepartmentEventListener implements ApplicationListener<DepartmentEvent> {
    @Override
    public void onApplicationEvent(DepartmentEvent event) {
        log.info("Department Event Listener: {}, {}", event.getDeptId(), event.getDeptName());
//        throw new RuntimeException();
    }
}
