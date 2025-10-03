package studentLife.demo.service.business.course;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studentLife.demo.domain.course.CourseEntity;
import studentLife.demo.repository.course.CourseRepository;
import studentLife.demo.repository.user.UserRepository;
import studentLife.demo.service.ResponseDTO;
import studentLife.demo.service.dto.course.CourseDTO;
import studentLife.demo.service.dto.course.crud.InsertCourseDTO;
import studentLife.demo.service.dto.course.crud.SearchCourseDTO;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ResponseDTO<CourseDTO> addCourse(InsertCourseDTO course) {
        // Lấy username từ JWT
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Tìm user theo username
        var user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));

        CourseEntity courseEntity = InsertCourseDTO.toEntity(course);
        courseEntity.setUser(user); // gắn user đang login

        courseEntity = courseRepository.save(courseEntity);

        ResponseDTO<CourseDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setData(CourseDTO.toDTO(courseEntity));
        responseDTO.setStatus(String.valueOf(HttpStatus.OK));
        return responseDTO;
    }

    // Sửa khóa học
    @Transactional
    public ResponseDTO<CourseDTO> updateCourse(String courseId, InsertCourseDTO courseDTO) {
        CourseEntity courseEntity = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course không tồn tại"));

        courseEntity.setNameCourse(courseDTO.getNameCourse());
        courseEntity.setDescription(courseDTO.getDescription());
        courseEntity.setRoom(courseDTO.getRoom());
        courseEntity.setDayWeek(courseDTO.getDayWeek());
        courseEntity.setTimeStudy(courseDTO.getTimeStudy());
        courseEntity.setTimeStudyEnd(courseDTO.getTimeStudyEnd());
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

    @Transactional(readOnly = true)
    public ResponseDTO<List<CourseDTO>> searchCourses(SearchCourseDTO dto) {
        var pageable = PageRequest.of(dto.getPageIndex(), dto.getPageSize());

        Page<CourseEntity> page = courseRepository.searchCourses(
                dto.getNameCourse(),
                dto.getDescription() != null ? dto.getDescription() : "",
                dto.getRoom() != null ? dto.getRoom() : "",
                dto.getDayWeek() != null ? dto.getDayWeek() : "",
                dto.getColor() != null ? dto.getColor() : "",
                dto.getTimeStudy() != null ? dto.getTimeStudy() : LocalDateTime.now(),
                dto.getTimeStudyEnd() != null ? dto.getTimeStudyEnd() : LocalDateTime.now(),
                pageable
        );

        // Lấy danh sách CourseDTO
        List<CourseDTO> courseList = page.stream()
                .map(CourseDTO::toDTO)
                .toList();

        ResponseDTO<List<CourseDTO>> responseDTO = new ResponseDTO<>();
        responseDTO.setData(courseList);
        responseDTO.setStatus(String.valueOf(HttpStatus.OK));
        return responseDTO;
    }

}






