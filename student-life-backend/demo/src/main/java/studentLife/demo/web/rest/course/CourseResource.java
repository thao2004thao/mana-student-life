package studentLife.demo.web.rest.course;

import org.springframework.web.bind.annotation.*;
import studentLife.demo.service.ResponseDTO;
import studentLife.demo.service.business.course.CourseService;
import studentLife.demo.service.dto.course.CourseDTO;
import studentLife.demo.service.dto.course.crud.InsertCourceDTO;

@RestController
@RequestMapping("/api/courses")
public class CourseResource {

    private final CourseService courseService;

    public CourseResource(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/add/{userId}")
    public ResponseDTO<CourseDTO> addCourse(@PathVariable String userId,
                                            @RequestBody InsertCourceDTO course) {
        return courseService.addCourse(userId, course);
    }

    @PutMapping("/update/{courseId}")
    public ResponseDTO<CourseDTO> updateCourse(@PathVariable String courseId,
                                               @RequestBody InsertCourceDTO courseDTO) {
        return courseService.updateCourse(courseId, courseDTO);
    }

    @DeleteMapping("/delete/{courseId}")
    public ResponseDTO<String> deleteCourse(@PathVariable String courseId) {
        return courseService.deleteCourse(courseId);
    }

}
