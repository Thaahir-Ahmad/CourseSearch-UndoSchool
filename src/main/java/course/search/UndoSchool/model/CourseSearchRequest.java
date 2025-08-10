package course.search.UndoSchool.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CourseSearchRequest {
    private String keyword;
    private String category;
    private String type;
    private Double minPrice;
    private Double maxPrice;
    private Integer minAge;
    private Integer maxAge;
    private String query;
    private LocalDate startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate nextSessionDate;

    private String sort; // "priceAsc", "priceDesc", or null
    private int page = 0;
    private int size = 10;

}
