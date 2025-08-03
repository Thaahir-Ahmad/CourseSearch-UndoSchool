package course.search.UndoSchool.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class CourseSearchReponseDto {
        private String id;
        private String title;
        private String description;
        private Double price;
        private String category;
        private String type;
        private Integer minAge;
        private Integer maxAge;
        private LocalDate nextSessionDate;
        // Getters & Setters

}
