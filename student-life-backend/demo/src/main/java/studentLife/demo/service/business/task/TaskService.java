package studentLife.demo.service.business.task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studentLife.demo.domain.task.TaskEntity;
import studentLife.demo.domain.course.CourseEntity;
import studentLife.demo.enums.task.TaskPriority;
import studentLife.demo.enums.task.TaskStatus;
import studentLife.demo.repository.task.TaskRepository;
import studentLife.demo.repository.course.CourseRepository;
import studentLife.demo.service.ResponseDTO;
import studentLife.demo.service.dto.task.TaskDTO;
import studentLife.demo.service.dto.task.crud.InsertTaskDTO;
import studentLife.demo.service.dto.task.crud.SearchTaskDTO;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final CourseRepository courseRepository;

    public TaskService(TaskRepository taskRepository, CourseRepository courseRepository) {
        this.taskRepository = taskRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public ResponseDTO<TaskDTO> addTask(InsertTaskDTO dto) {
        TaskEntity entity = InsertTaskDTO.toEntity(dto);
        entity = taskRepository.save(entity);

        ResponseDTO<TaskDTO> response = new ResponseDTO<>();
        response.setData(TaskDTO.toDTO(entity));
        response.setStatus(String.valueOf(HttpStatus.OK));
        return response;
    }

    @Transactional
    public ResponseDTO<TaskDTO> updateTask(String id, InsertTaskDTO dto) {
        TaskEntity entity = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task không tồn tại"));

        if(dto.getTitle() != null) entity.setTitle(dto.getTitle());
        if(dto.getDescription() != null) entity.setDescription(dto.getDescription());
        if(dto.getStatus() != null) entity.setStatus(TaskStatus.valueOf(dto.getStatus()));
        if(dto.getPriority() != null) entity.setPriority(TaskPriority.valueOf(dto.getPriority()));
        if(dto.getDeadline() != null) entity.setDeadline(dto.getDeadline());

        entity = taskRepository.save(entity);

        ResponseDTO<TaskDTO> response = new ResponseDTO<>();
        response.setData(TaskDTO.toDTO(entity));
        response.setStatus(String.valueOf(HttpStatus.OK));
        return response;
    }

    @Transactional
    public ResponseDTO<String> deleteTask(String id) {
        TaskEntity entity = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task không tồn tại"));

        taskRepository.delete(entity);

        ResponseDTO<String> response = new ResponseDTO<>();
        response.setData("Đã xóa task với ID: " + id);
        response.setStatus(String.valueOf(HttpStatus.OK));
        return response;
    }

    @Transactional(readOnly = true)
    public ResponseDTO<Page<TaskDTO>> searchTasks(SearchTaskDTO dto) {
        var pageable = PageRequest.of(dto.getPageIndex(), dto.getPageSize());

        Page<TaskEntity> page = taskRepository.searchTasks(
                dto.getTitle(),
                dto.getDescription(),
                dto.getStatus(),
                dto.getPriority(),
                dto.getDeadline(),
                pageable
        );

        ResponseDTO<Page<TaskDTO>> response = new ResponseDTO<>();
        response.setData(page.map(TaskDTO::toDTO));
        response.setStatus(String.valueOf(HttpStatus.OK));
        return response;
    }
}
