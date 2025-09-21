package studentLife.demo.service.business.course;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studentLife.demo.domain.course.CourseEntity;
import studentLife.demo.repository.course.CourseRepository;
import studentLife.demo.repository.user.UserRepository;
import studentLife.demo.service.ResponseDTO;
import studentLife.demo.service.dto.course.CourseDTO;
import studentLife.demo.service.dto.course.crud.InsertCourceDTO;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ResponseDTO<CourseDTO> addCourse(String userId, InsertCourceDTO course) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));

        CourseEntity courseEntity = InsertCourceDTO.toEntity(course);
        courseEntity.setUser(user);

        courseEntity = courseRepository.save(courseEntity);

        ResponseDTO<CourseDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setData(CourseDTO.toDTO(courseEntity));
        responseDTO.setStatus(String.valueOf(HttpStatus.OK));

        return responseDTO;
    }

    // Sửa khóa học
    @Transactional
    public ResponseDTO<CourseDTO> updateCourse(String courseId, InsertCourceDTO courseDTO) {
        CourseEntity courseEntity = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course không tồn tại"));

        courseEntity.setNameCourse(courseDTO.getNameCourse());
        courseEntity.setDescription(courseDTO.getDescription());
        courseEntity.setRoom(courseDTO.getRoom());
        courseEntity.setDayWeek(courseDTO.getDayWeek());
        courseEntity.setTimeStudy(courseDTO.getTimeStudy());
        courseEntity.setColor(courseDTO.getColor());

        courseEntity = courseRepository.save(courseEntity);

        ResponseDTO<CourseDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setData(CourseDTO.toDTO(courseEntity));
        responseDTO.setStatus(String.valueOf(HttpStatus.OK));
        return responseDTO;
    }

    // Xóa khóa học
    @Transactional
    public ResponseDTO<String> deleteCourse(String courseId) {
        CourseEntity courseEntity = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course không tồn tại"));

        courseRepository.delete(courseEntity);

        ResponseDTO<String> responseDTO = new ResponseDTO<>();
        responseDTO.setData("Đã xóa khóa học với ID: " + courseId);
        responseDTO.setStatus(String.valueOf(HttpStatus.OK));
        return responseDTO;
    }





}
