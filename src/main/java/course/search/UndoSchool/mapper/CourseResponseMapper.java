package course.search.UndoSchool.mapper;

import course.search.UndoSchool.dto.CourseSearchReponseDto;
import course.search.UndoSchool.model.CourseDocument;
import course.search.UndoSchool.model.CourseSearchResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseResponseMapper {
    public List<CourseSearchReponseDto> mapToDtoList(CourseSearchResponse response) {
        return response.getCourses().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private CourseSearchReponseDto mapToDto(CourseDocument doc) {
        return CourseSearchReponseDto.builder()
                .id(doc.getId())
                .title(doc.getTitle())
                .description(doc.getDescription())
                .category(doc.getCategory())
                .type(doc.getType())
                .minAge(doc.getMinAge())
                .maxAge(doc.getMaxAge())
                .price(doc.getPrice())
                .nextSessionDate(doc.getNextSessionDate())
                .build();
    }

}
