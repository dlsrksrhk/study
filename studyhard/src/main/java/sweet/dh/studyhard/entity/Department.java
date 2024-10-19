package sweet.dh.studyhard.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "departments"})
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String deptName;

    @ManyToMany(mappedBy = "departments")
    private List<User> members = new ArrayList<>();

}
