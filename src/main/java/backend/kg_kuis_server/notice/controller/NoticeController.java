package backend.kg_kuis_server.notice.controller;

import backend.kg_kuis_server.notice.service.NoticeService;
import backend.kg_kuis_server.notice.service.dto.NoticeResponse;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notices")
@Tag(name = "공지사항 API", description = "공지사항 API")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    @Operation(
            summary = "공지사항 조회 (카테고리/검색)",
            description = """
                    카테고리 ID와 키워드를 조건으로 공지사항을 조회합니다.
                    - categoryId만 있으면: 해당 카테고리 전체 조회
                    - keyword만 있으면: 제목 검색
                    - 둘 다 있으면: 해당 카테고리 내 검색
                    - 둘 다 없으면: 전체 공지사항 조회
                    - 학사: 234, 장학 235, 국제: 237, 학생: 238, 일반: 240, 취창업: 4083, 산학: 4214 
                    - 결과는 pubDate 기준 내림차순으로 정렬되며,
                    북마크된 공지사항은 항상 상단에 고정됩니다.
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "조회 성공",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = NoticeResponse.class)))
                    )
            }
    )
    @PageableAsQueryParam
    public ResponseEntity<backend.kg_kuis_server.notice.dto.PageResponse<NoticeResponse>> getNotices(
            @Parameter(description = "카테고리 ID", example = "234")
            @RequestParam(required = false) Integer categoryId,

            @Parameter(description = "검색 키워드 (제목 부분일치)", example = "장학금")
            @RequestParam(required = false) String keyword,

            @ParameterObject
            @PageableDefault(size = 10, sort = "pubDate", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        backend.kg_kuis_server.notice.dto.PageResponse<NoticeResponse> result = noticeService.getNotices(categoryId, keyword, pageable);
        return ResponseEntity.ok(result);
    }
}
