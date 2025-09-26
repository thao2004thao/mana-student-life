package studentLife.demo.service.dto.task.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SearchTaskDTO {

    private int pageSize;
    private int pageIndex;

    private String title;
    private String description;
    private String status;
    private String priority;
    private LocalDateTime deadline;
}
