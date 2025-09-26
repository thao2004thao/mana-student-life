package studentLife.demo.web.rest.task;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import studentLife.demo.service.ResponseDTO;
import studentLife.demo.service.business.task.TaskService;
import studentLife.demo.service.dto.task.TaskDTO;
import studentLife.demo.service.dto.task.crud.InsertTaskDTO;
import studentLife.demo.service.dto.task.crud.SearchTaskDTO;

@RestController
@RequestMapping("/api/tasks")
public class TaskResource {

    private final TaskService taskService;

    public TaskResource(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/add")
    public ResponseDTO<TaskDTO> addTask(@RequestBody InsertTaskDTO dto) {
        return taskService.addTask(dto);
    }

    @PutMapping("/update/{id}")
    public ResponseDTO<TaskDTO> updateTask(@PathVariable String id,
                                           @RequestBody InsertTaskDTO dto) {
        return taskService.updateTask(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO<String> deleteTask(@PathVariable String id) {
        return taskService.deleteTask(id);
    }

    @PostMapping("/search")
    public ResponseDTO<Page<TaskDTO>> searchTasks(@RequestBody SearchTaskDTO dto) {
        return taskService.searchTasks(dto);
    }
}
