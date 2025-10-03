package studentLife.demo.service.dto.course.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
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
    private String color;
    private LocalDateTime timeStudy;
    private LocalDateTime timeStudyEnd;


}
