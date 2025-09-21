package studentLife.demo.repository.course;

import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import studentLife.demo.domain.course.CourseEntity;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity,String> {
    CourseEntity findOneById(String id);
    CourseEntity findOneByNameCourse(String nameCourse);

}
