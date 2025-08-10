package course.search.UndoSchool.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import course.search.UndoSchool.model.CourseDocument;
import course.search.UndoSchool.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import java.io.InputStream;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CourseDataLoader {
    private final CourseRepository courseRepository;
    private final ObjectMapper objectMapper;

    @EventListener(ApplicationReadyEvent.class)
    public void loadSampleData() {
        try {
            InputStream courseJsonData = new ClassPathResource("sample-courses.json").getInputStream();
            List<CourseDocument> courses = objectMapper.readValue(courseJsonData, new TypeReference<>() {});
            courseRepository.saveAll(courses);

            System.out.println("✅ Sample courses indexed into Elasticsearch.");
        } catch (Exception e) {
            System.err.println("❌ Failed to load sample data: " + e.getMessage());
        }
    }
}
