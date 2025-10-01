package studentLife.demo.repository.course;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import studentLife.demo.domain.course.CourseEntity;

import java.time.LocalDateTime;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, String> {

    CourseEntity findOneById(String id);
    CourseEntity findOneByNameCourse(String nameCourse);

    @Query("""
SELECT c FROM CourseEntity c
WHERE (:nameCourse IS NULL OR LOWER(c.nameCourse) LIKE LOWER(CONCAT('%', :nameCourse, '%')))
  AND (:description IS NULL OR LOWER(c.description) LIKE LOWER(CONCAT('%', :description, '%')))
  AND (:room IS NULL OR LOWER(c.room) LIKE LOWER(CONCAT('%', :room, '%')))
  AND (:dayWeek IS NULL OR LOWER(c.dayWeek) LIKE LOWER(CONCAT('%', :dayWeek, '%')))
  AND (:color IS NULL OR LOWER(c.color) LIKE LOWER(CONCAT('%', :color, '%')))
  AND (:timeStudy IS NULL OR c.timeStudy = :timeStudy)
""")
    Page<CourseEntity> searchCourses(
            @Param("nameCourse") String nameCourse,
            @Param("description") String description,
            @Param("room") String room,
            @Param("dayWeek") String dayWeek,
            @Param("color") String color,
            @Param("timeStudy") LocalDateTime timeStudy,
            LocalDateTime timeStudyEnd, Pageable pageable
    );


}

