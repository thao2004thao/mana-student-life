package studentLife.demo.service.dto.course.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import studentLife.demo.domain.course.CourseEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class InsertCourceDTO {

    private String nameCourse;
    private String description;
    private String room;
    private String dayWeek;
    private LocalDateTime timeStudy;
    private String color;

    public static CourseEntity toEntity(InsertCourceDTO courseDTO){
        if(courseDTO == null) return null;

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setRoom(courseDTO.getRoom());
        courseEntity.setDayWeek(courseDTO.getDayWeek());
        courseEntity.setColor(courseDTO.getColor());
        courseEntity.setDescription(courseDTO.getDescription());
        courseEntity.setNameCourse(courseDTO.getNameCourse());
        courseEntity.setTimeStudy(courseDTO.getTimeStudy());
        return courseEntity;
    }
}
