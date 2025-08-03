package course.search.UndoSchool.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@Component
public class CourseSearchResponse {

        private String id;
        private String title;
        private String description;
        private Double price;
        private String category;
        private String type;
        private Integer minAge;
        private Integer maxAge;
        private LocalDate nextSessionDate;
        // Getters & Setter
}
