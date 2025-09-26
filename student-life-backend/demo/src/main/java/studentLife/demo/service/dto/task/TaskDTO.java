package studentLife.demo.service.dto.task;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import studentLife.demo.domain.task.TaskEntity;
import studentLife.demo.service.AbstractDTO;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TaskDTO extends AbstractDTO<String> {

    private String id;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private String status;
    private String priority;
    private String courseId;

    public static TaskDTO toDTO(TaskEntity entity) {
        if (entity == null) return null;

        TaskDTO dto = new TaskDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setDeadline(entity.getDeadline());
        dto.setStatus(entity.getStatus());
        dto.setPriority(entity.getPriority());
        dto.setCourseId(entity.getCourse() != null ? entity.getCourse().getId() : null);
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setLastModifiedBy(entity.getLastModifiedBy());
        dto.setLastModifiedDate(entity.getLastModifiedDate());

        return dto;
    }
}
