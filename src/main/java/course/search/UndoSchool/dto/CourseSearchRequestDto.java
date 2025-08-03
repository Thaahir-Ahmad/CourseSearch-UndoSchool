package course.search.UndoSchool.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class CourseSearchRequestDto {
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
