package backend.kg_kuis_server.notice.controller;

import backend.kg_kuis_server.notice.repository.entity.NoticeEntity;
import backend.kg_kuis_server.notice.service.NoticeService;
import backend.kg_kuis_server.notice.service.dto.NoticeResponse;
import backend.kg_kuis_server.notice.service.dto.NoticeSimpleResponse;
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
            summary = "공지사항 카테고리별 조회",
            description = "지정한 카테고리 ID에 해당하는 공지사항 목록을 페이징하여 반환합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "조회 성공",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = NoticeResponse.class)))
                    )
            }
    )
    @PageableAsQueryParam
    public Page<NoticeResponse> getNoticesByCategory(
            @Parameter(description = "234: 학사," +
                    " 235: 장학," +
                    " 237: 국제," +
                    " 238: 학생," +
                    " 4083: 취창업," +
                    " 240: 일반," +
                    " 4214: 산학", example = "234")
            @RequestParam(value = "category", required = false) Integer categoryId,

            @ParameterObject Pageable pageable
    ) {
        return noticeService.findByCategory(categoryId, pageable);
    }

    @GetMapping("/search")
    @Operation(
            summary = "공지사항 검색",
            description = """
                    키워드와 선택적 카테고리 ID로 공지사항을 검색합니다.
                    결과는 pubDate 기준 내림차순으로 정렬됩니다.
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "검색 성공",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = NoticeSimpleResponse.class)))
                    )
            }
    )
    @PageableAsQueryParam
    public ResponseEntity<Page<NoticeSimpleResponse>> search(
            @Parameter(description = "검색 키워드 (제목 부분일치)", example = "장학금")
            @RequestParam(required = false) String keyword,

            @Parameter(description = "카테고리 ID", example = "2")
            @RequestParam(required = false) Integer categoryId,

            @ParameterObject
            @PageableDefault(size = 10, sort = "pubDate", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<NoticeSimpleResponse> result = noticeService.searchNotices(keyword, categoryId, pageable);
        return ResponseEntity.ok(result);
    }
}