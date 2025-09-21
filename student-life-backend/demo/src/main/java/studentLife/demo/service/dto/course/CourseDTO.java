package studentLife.demo.service.dto.course;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import studentLife.demo.domain.course.CourseEntity;
import studentLife.demo.service.AbstractDTO;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CourseDTO extends AbstractDTO<String> {

    private String id;
    private String nameCourse;
    private String description;
    private String room;
    private String dayWeek;
    private LocalDateTime timeStudy;
    private String color;

    public static CourseDTO toDTO(CourseEntity entity) {
        if (entity == null) return null;

        CourseDTO dto = new CourseDTO();
        dto.setId(entity.getId());
        dto.setNameCourse(entity.getNameCourse());
        dto.setDescription(entity.getDescription());
        dto.setRoom(entity.getRoom());
        dto.setDayWeek(entity.getDayWeek());
        dto.setTimeStudy(entity.getTimeStudy());
        dto.setColor(entity.getColor());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setLastModifiedBy(entity.getLastModifiedBy());
        dto.setLastModifiedDate(entity.getLastModifiedDate());

        return dto;
    }
}
