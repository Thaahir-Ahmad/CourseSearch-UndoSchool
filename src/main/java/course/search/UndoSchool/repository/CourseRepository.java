package course.search.UndoSchool.repository;

import course.search.UndoSchool.model.CourseDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends ElasticsearchRepository<CourseDocument, String> {

}
