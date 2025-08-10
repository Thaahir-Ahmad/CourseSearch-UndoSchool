package course.search.UndoSchool.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import course.search.UndoSchool.model.CourseDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CourseRepository {
    private final ElasticsearchClient elasticsearchClient;
    public void save(CourseDocument course) throws IOException {
        elasticsearchClient.index(IndexRequest.of(i -> i
                .index("courses")
                .id(course.getId())
                .document(course)
        ));
    }

    public void saveAll(List<CourseDocument> courses) throws IOException {
        for (CourseDocument course : courses) {
            save(course);
        }
    }
}
