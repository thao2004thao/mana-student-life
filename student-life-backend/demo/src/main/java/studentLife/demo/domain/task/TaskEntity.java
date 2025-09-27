package studentLife.demo.domain.task;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import studentLife.demo.domain.AbstractEntity;
import studentLife.demo.domain.course.CourseEntity;
import studentLife.demo.enums.task.TaskPriority;
import studentLife.demo.enums.task.TaskStatus;

import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="tbl_task")
@EntityListeners(AuditingEntityListener.class)
public class TaskEntity extends AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @Column(name = "status")
    private TaskStatus status;

    @Column(name = "priority")
    private TaskPriority priority;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;
}
