package studentLife.demo.service.dto.course.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import studentLife.demo.domain.course.CourseEntity;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class UpdateCourseDTO {
    private String nameCourse;
    private String description;
    private String room;
    private String dayWeek;
    private LocalDateTime timeStudy;
    private LocalDateTime timeStudyEnd;
    private String color;

    public static CourseEntity toEntity(UpdateCourseDTO updateCourseDTO){
        if(updateCourseDTO == null) return null;

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setRoom(updateCourseDTO.getRoom());
        courseEntity.setDayWeek(updateCourseDTO.getDayWeek());
        courseEntity.setColor(updateCourseDTO.getColor());
        courseEntity.setDescription(updateCourseDTO.getDescription());
        courseEntity.setNameCourse(updateCourseDTO.getNameCourse());
        courseEntity.setTimeStudy(updateCourseDTO.getTimeStudy());
        courseEntity.setTimeStudyEnd(updateCourseDTO.getTimeStudyEnd());
        return courseEntity;

    }


}
