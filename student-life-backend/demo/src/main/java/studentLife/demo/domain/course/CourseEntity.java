package studentLife.demo.domain.course;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import studentLife.demo.domain.AbstractEntity;
import studentLife.demo.domain.task.TaskEntity;
import studentLife.demo.domain.user.UserEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tbl_course")
@EntityListeners(AuditingEntityListener.class)
public class CourseEntity extends AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name_cource")
    private String nameCourse;
    @Column(name = "description")
    private String description;
    @Column(name = "room")
    private String room;
    @Column(name = "day_Week")
    private String dayWeek;
    @Column(name = "time_study")
    private LocalDateTime timeStudy;
    @Column(name = "color")
    private String color;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskEntity> tasks;

}
