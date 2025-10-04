package studentLife.demo.repository.task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import studentLife.demo.domain.task.TaskEntity;

import java.time.LocalDateTime;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, String> {

    TaskEntity findOneById(String id);

    @Query("""
    SELECT t FROM TaskEntity t
    WHERE t.title = COALESCE(:title, t.title)
      AND t.description = COALESCE(:description, t.description)
      AND t.status = COALESCE(:status, t.status)
      AND t.priority = COALESCE(:priority, t.priority)
      AND t.deadline = COALESCE(:deadline, t.deadline)
""")
    Page<TaskEntity> searchTasks(
            @Param("title") String title,
            @Param("description") String description,
            @Param("status") String status,
            @Param("priority") String priority,
            @Param("deadline") LocalDateTime deadline,
            Pageable pageable
    );

}
