package course.search.UndoSchool.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CourseSearchRequest {
    private String keyword;
    private String category;
    private String type;
    private Double minPrice;
    private Double maxPrice;
    private Integer minAge;
    private Integer maxAge;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate nextSessionDate;

    private String sort; // "priceAsc", "priceDesc", or null
    private int page = 0;
    private int size = 10;

}
