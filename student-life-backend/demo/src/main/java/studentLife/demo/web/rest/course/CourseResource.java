package studentLife.demo.web.rest.course;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import studentLife.demo.service.ResponseDTO;
import studentLife.demo.service.business.course.CourseService;
import studentLife.demo.service.dto.course.CourseDTO;
import studentLife.demo.service.dto.course.crud.InsertCourseDTO;
import studentLife.demo.service.dto.course.crud.SearchCourseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseResource {

    private final CourseService courseService;

    public CourseResource(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/add")
    public ResponseDTO<CourseDTO> addCourse(@RequestBody InsertCourseDTO course) {
        return courseService.addCourse(course);
    }

    @PutMapping("/update/{courseId}")
    public ResponseDTO<CourseDTO> updateCourse(@PathVariable String courseId,
                                               @RequestBody InsertCourseDTO courseDTO) {
        return courseService.updateCourse(courseId, courseDTO);
    }

    @DeleteMapping("/delete/{courseId}")
    public ResponseDTO<String> deleteCourse(@PathVariable String courseId) {
        return courseService.deleteCourse(courseId);
    }
    @PostMapping("/search")
    public ResponseDTO<List<CourseDTO>> searchCourses(@RequestBody SearchCourseDTO dto) {
        return courseService.searchCourses(dto);
    }
    @GetMapping("/my-courses")
    public ResponseDTO<List<CourseDTO>> getMyCourses() {
        return courseService.getCoursesOfCurrentUser();
    }



}
