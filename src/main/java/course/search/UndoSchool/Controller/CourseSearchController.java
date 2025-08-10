package course.search.UndoSchool.Controller;
import course.search.UndoSchool.dto.CourseSearchReponseDto;
import course.search.UndoSchool.dto.SearchResponseDto;
import course.search.UndoSchool.mapper.CourseRequestMapper;
import course.search.UndoSchool.model.CourseSearchRequest;
import course.search.UndoSchool.service.CourseSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CourseSearchController {
    List<CourseSearchReponseDto> courses;
    @Autowired
    private CourseSearchService courseSearchService;
    @Autowired
    private CourseRequestMapper courseRequestMapper;
    @GetMapping("/search")
    public SearchResponseDto searchCourses(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(defaultValue = "upcoming") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        CourseSearchRequest request = CourseSearchRequest.builder().query(q)
                .minAge(minAge)
                .maxAge(maxAge)
                .category(category)
                .type(type)
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .startDate(startDate)
                .sort(sort)
                .page(page)
                .size(size)
                .build();

            try {
                courses = courseSearchService.search(courseRequestMapper.mapToCourseSearchRequestDto(request));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        long total = courses.size(); // fallback: count from list size
        return new SearchResponseDto(total, courses);
    }

}
