package course.search.UndoSchool.dto;

import java.util.List;

public record SearchResponseDto(long total, List<CourseSearchReponseDto> courses) {

}
