package sweet.dh.studyhard.corp.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class DepartmentEvent extends ApplicationEvent {
    private final Long deptId;
    private final String deptName;

    private DepartmentEvent(Object source, Long deptId, String deptName) {
        super(source);
        this.deptId = deptId;
        this.deptName = deptName;
    }

    public static DepartmentEvent of(Object source, Long deptId, String deptName) {
        return new DepartmentEvent(source, deptId, deptName);
    }
}
