package course.search.UndoSchool.mapper;

import course.search.UndoSchool.dto.CourseSearchRequestDto;
import course.search.UndoSchool.model.CourseSearchRequest;
import org.springframework.stereotype.Component;

@Component
public class CourseRequestMapper {
    public CourseSearchRequest mapToCourseRequest(CourseSearchRequestDto courseSearchRequestDto) {
        return CourseSearchRequest.builder()
                .keyword(courseSearchRequestDto.getKeyword())
                .category(courseSearchRequestDto.getCategory())
                .type(courseSearchRequestDto.getType())
                .minAge(courseSearchRequestDto.getMinAge())
                .maxAge(courseSearchRequestDto.getMaxAge())
                .minPrice(courseSearchRequestDto.getMinPrice())
                .maxPrice(courseSearchRequestDto.getMaxPrice())
                .nextSessionDate(courseSearchRequestDto.getNextSessionDate())
                .sort(courseSearchRequestDto.getSort())
                .page(courseSearchRequestDto.getPage())
                .size(courseSearchRequestDto.getSize())
                .build();
    }
    public CourseSearchRequestDto mapToCourseSearchRequestDto(CourseSearchRequest courseSearchRequest) {
        return CourseSearchRequestDto.builder()
                .keyword(courseSearchRequest.getKeyword())
                .category(courseSearchRequest.getCategory())
                .type(courseSearchRequest.getType())
                .minAge(courseSearchRequest.getMinAge())
                .maxAge(courseSearchRequest.getMaxAge())
                .minPrice(courseSearchRequest.getMinPrice())
                .maxPrice(courseSearchRequest.getMaxPrice())
                .nextSessionDate(courseSearchRequest.getNextSessionDate())
                .sort(courseSearchRequest.getSort())
                .page(courseSearchRequest.getPage())
                .size(courseSearchRequest.getSize())
                .build();
    }
}
