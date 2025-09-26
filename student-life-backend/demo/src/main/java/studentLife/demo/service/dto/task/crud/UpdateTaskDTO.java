package studentLife.demo.service.dto.task.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import studentLife.demo.domain.task.TaskEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UpdateTaskDTO {

    private String title;
    private String description;
    private LocalDateTime deadline;
    private String status;
    private String priority;
    private String courseId;

    public static TaskEntity toEntity(UpdateTaskDTO dto) {
        if (dto == null) return null;

        TaskEntity entity = new TaskEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setDeadline(dto.getDeadline());
        entity.setStatus(dto.getStatus());
        entity.setPriority(dto.getPriority());
        return entity;
    }
}
