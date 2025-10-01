package studentLife.demo.service.dto.course.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SearchCourseDTO {
    private int pageSize;
    private int pageIndex;

    private String nameCourse;
    private String description;
    private String room;
    private String dayWeek;
    private LocalDateTime timeStudy;
    private LocalDateTime timeStudyEnd;
    private String color;


}
