package course.search.UndoSchool.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.json.JsonData;

import course.search.UndoSchool.dto.CourseSearchReponseDto;
import course.search.UndoSchool.dto.CourseSearchRequestDto;
import course.search.UndoSchool.dto.CourseSearchResponseDto;
import course.search.UndoSchool.mapper.CourseRequestMapper;
import course.search.UndoSchool.mapper.CourseResponseMapper;
import course.search.UndoSchool.model.CourseDocument;
import course.search.UndoSchool.model.CourseSearchRequest;
import course.search.UndoSchool.model.CourseSearchResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseSearchService {

    private final ElasticsearchClient elasticsearchClient;
    private final CourseRequestMapper courseRequestMapper;
    private final CourseResponseMapper courseResponseMapper;

    public CourseSearchReponseDto search(CourseSearchRequestDto requestDto) throws IOException {
        CourseSearchRequest request = courseRequestMapper.mapToCourseRequest(requestDto);

        List<Query> mustQueries = new ArrayList<>();
        List<Query> filterQueries = new ArrayList<>();

        // Full-text search on title and description (multi_match)
        if (request.getKeyword() != null && !request.getKeyword().isEmpty()) {
            mustQueries.add(Query.of(q -> q
                    .multiMatch(m -> m
                            .fields("title", "description")
                            .query(request.getKeyword())
                    )
            ));
        }

        // Filters
        if (request.getMinAge() != null || request.getMaxAge() != null) {
            RangeQuery.Builder range = new RangeQuery.Builder().field("minAge");
            if (request.getMinAge() != null) range.gte(JsonData.of(request.getMinAge()));
            if (request.getMaxAge() != null) range.lte(JsonData.of(request.getMaxAge()));
            filterQueries.add(Query.of(q -> q.range(range.build())));
        }

        if (request.getMinPrice() != null || request.getMaxPrice() != null) {
            RangeQuery.Builder range = new RangeQuery.Builder().field("price");
            if (request.getMinPrice() != null) range.gte(JsonData.of(request.getMinPrice()));
            if (request.getMaxPrice() != null) range.lte(JsonData.of(request.getMaxPrice()));
            filterQueries.add(Query.of(q -> q.range(range.build())));
        }

        if (request.getCategory() != null) {
            filterQueries.add(Query.of(q -> q.term(t -> t
                    .field("category.keyword")
                    .value(request.getCategory())
            )));
        }

        if (request.getType() != null) {
            filterQueries.add(Query.of(q -> q.term(t -> t
                    .field("type.keyword")
                    .value(request.getType())
            )));
        }

        if (request.getNextSessionDate() != null) {
            filterQueries.add(Query.of(q -> q.range(r -> r
                    .field("nextSessionDate")
                    .gte(JsonData.of(request.getNextSessionDate()))
            )));
        }

        // Combine must and filter into a bool query
        BoolQuery boolQuery = BoolQuery.of(b -> b
                .must(mustQueries)
                .filter(filterQueries)
        );

        // Sorting
        List<SortOptions> sortOptions = new ArrayList<>();
        if ("priceAsc".equals(request.getSort())) {
            sortOptions.add(SortOptions.of(s -> s.field(f -> f.field("price").order(SortOrder.Asc))));
        } else if ("priceDesc".equals(request.getSort())) {
            sortOptions.add(SortOptions.of(s -> s.field(f -> f.field("price").order(SortOrder.Desc))));
        } else {
            // Default: sort by nextSessionDate ascending
            sortOptions.add(SortOptions.of(s -> s.field(f -> f.field("nextSessionDate").order(SortOrder.Asc))));
        }

        // Pagination
        int page = request.getPage() != null ? request.getPage() : 0;
        int size = request.getSize() != null ? request.getSize() : 10;
        int from = page * size;

        // Build search request
        SearchRequest searchRequest = SearchRequest.of(s -> s
                .index("courses")
                .query(Query.of(q -> q.bool(boolQuery)))
                .from(from)
                .size(size)
                .sort(sortOptions)
        );

        SearchResponse<CourseDocument> response = elasticsearchClient.search(searchRequest, CourseDocument.class);

        List<CourseDocument> results = response.hits().hits().stream()
                .map(hit -> hit.source())
                .toList();

        CourseSearchResponse courseSearchResponse = new CourseSearchResponse(results, response.hits().total().value());
        return courseResponseMapper.mapToDto(courseSearchResponse);
    }
}
