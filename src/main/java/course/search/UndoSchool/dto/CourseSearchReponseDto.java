package course.search.UndoSchool.dto;

import course.search.UndoSchool.model.CourseDocument;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class CourseSearchReponseDto {
        private String id;
        private String title;
        private String category;
        private Double price;
        private LocalDate nextSessionDate;
        private String description;
        private String type;
        private int minAge;
        private int maxAge;
}