package course.search.UndoSchool.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class CourseSearchResponse {
        private List<CourseDocument> results;
        private long totalCount;
        public CourseSearchResponse(List<CourseDocument> results, long totalCount) {
                this.results = results;
                this.totalCount = totalCount;
        }
}
