package studentLife.demo.repository.course;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import studentLife.demo.domain.course.CourseEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, String> {

    CourseEntity findOneById(String id);
    CourseEntity findOneByNameCourse(String nameCourse);


    @Query("""
    SELECT c FROM CourseEntity c
    WHERE c.nameCourse = COALESCE(:nameCourse, c.nameCourse)
      AND c.description = COALESCE(:description, c.description)
      AND c.room = COALESCE(:room, c.room)
      AND c.dayWeek = COALESCE(:dayWeek, c.dayWeek)
      AND c.color = COALESCE(:color, c.color)
      AND c.timeStudy = COALESCE(:timeStudy, c.timeStudy)
      AND c.timeStudyEnd = COALESCE(:timeStudyEnd, c.timeStudyEnd)
""")
    Page<CourseEntity> searchCourses(
            @Param("nameCourse") String nameCourse,
            @Param("description") String description,
            @Param("room") String room,
            @Param("dayWeek") String dayWeek,
            @Param("color") String color,
            @Param("timeStudy") LocalDateTime timeStudy,
            @Param("timeStudyEnd") LocalDateTime timeStudyEnd,
            Pageable pageable
    );



}

