package sweet.dh.studyhard.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString(of = {"id", "userName"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;

    @ManyToMany
    @JoinTable(
            name = "department_members", // 중간 테이블 이름
            joinColumns = @JoinColumn(name = "user_id"), // 현재 엔티티와 연결된 외래 키
            inverseJoinColumns = @JoinColumn(name = "department_id") // 반대편 엔티티와 연결된 외래 키
    )
    private List<Department> departments = new ArrayList<>();
}
