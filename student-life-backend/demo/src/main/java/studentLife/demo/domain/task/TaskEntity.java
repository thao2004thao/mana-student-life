package studentLife.demo.domain.task;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import studentLife.demo.domain.AbstractEntity;
import studentLife.demo.domain.course.CourseEntity;

import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="task")
public class TaskEntity extends AbstractEntity implements Serializable {
    @Id
    private String id;

    @Column(name = "title")
    private String title; // tiêu đề công việc

    @Column(name = "description")
    private String description; // mô tả chi tiết

    @Column(name = "deadline")
    private LocalDateTime deadline; // hạn nộp / hạn hoàn thành

    @Column(name = "status")
    private String status; // trạng thái: TODO, IN_PROGRESS, DONE

    @Column(name = "priority")
    private String priority; // mức độ ưu tiên: LOW, MEDIUM, HIGH

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course; // liên kết với môn học
}
