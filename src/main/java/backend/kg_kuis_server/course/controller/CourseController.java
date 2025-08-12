package backend.kg_kuis_server.course.controller;

import backend.kg_kuis_server.course.service.CourseService;
import backend.kg_kuis_server.course.service.response.CourseOfferingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
@Tag(name = "Course Offerings", description = "종합강의시간표 조회 API")
public class CourseController {

    private final CourseService service;

    @GetMapping
    @Operation(
            summary = "강의 목록 조회",
            description = """
                    여러 조건으로 강의 목록을 검색합니다. 모든 파라미터는 선택이며, 기본 정렬은 id 오름차순입니다.
                    페이징은 `page`(0부터 시작), `size`, `sort`(예: `sort=courseCode,asc`)를 사용하세요.
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "조회 성공",
                            content = @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = CourseOfferingResponse.class))
                            )
                    )
            }
    )
    @PageableAsQueryParam  // Pageable 파라미터(page,size,sort) 문서화
    public Page<CourseOfferingResponse> list(
            @Parameter(
                    description = "학년도 (예: 2025)",
                    example = "2025",
                    schema = @Schema(type = "integer", minimum = "2000", maximum = "2100")
            )
            @RequestParam(required = false) Integer year,

            @Parameter(
                    description = "학기",
                    example = "일학기",
                    schema = @Schema(allowableValues = {"일학기", "이학기"})
            )
            @RequestParam(required = false) String semester,

            @Parameter(
                    description = "이수구분",
                    example = "전필",
                    schema = @Schema(allowableValues = {"교선", "교필", "반교", "일선", "전선", "전필"})
            )
            @RequestParam(required = false) String category,

            @Parameter(
                    description = "담당 교수명 (부분일치)",
                    example = "홍길동"
            )
            @RequestParam(required = false) String professor,

            @Parameter(
                    description = "과목 코드 (정확/부분일치)",
                    example = "CSE101"
            )
            @RequestParam(required = false) String courseCode,

            @Parameter(
                    name = "department",
                    description = "학과(부)명 (부분일치)",
                    example = "컴퓨터공학부"
            )
            @RequestParam(required = false, name = "department") String departmentName,

            @ParameterObject
            @PageableDefault(size = 20, sort = "id") Pageable pageable
    ) {
        return service.search(year, semester, category, professor, courseCode, departmentName, pageable);
    }
}