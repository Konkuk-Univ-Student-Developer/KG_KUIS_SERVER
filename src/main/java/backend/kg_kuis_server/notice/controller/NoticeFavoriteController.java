package backend.kg_kuis_server.notice.controller;

import backend.kg_kuis_server.notice.service.NoticeFavoriteService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notices")
@RequiredArgsConstructor
@Tag(name = "Notice Favorites", description = "공지 즐겨찾기(북마크) API - 고정 사용자 기준")
public class NoticeFavoriteController {

    private final NoticeFavoriteService favoriteService;

    @PostMapping("/{noticeId}/bookmark")
    @Operation(
            summary = "공지 북마크 추가",
            description = "고정된 1번 사용자의 즐겨찾기에 공지를 추가합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "추가 성공"),
                    @ApiResponse(responseCode = "404", description = "공지 없음")
            }
    )
    public ResponseEntity<Void> bookmark(
            @Parameter(description = "공지 ID", example = "1154366")
            @PathVariable(name = "noticeId") Long noticeId
    ) {
        favoriteService.mark(noticeId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{noticeId}/bookmark")
    @Operation(
            summary = "공지 북마크 해제",
            description = "고정된 1번 사용자의 즐겨찾기에서 공지를 제거합니다.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "해제 성공"),
                    @ApiResponse(responseCode = "404", description = "북마크 내역 없음")
            }
    )
    public ResponseEntity<Void> unbookmark(
            @Parameter(description = "공지 ID", example = "1154366")
            @PathVariable(name = "noticeId") Long noticeId
    ) {
        favoriteService.unmark(noticeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/bookmarks")
    @Operation(
            summary = "내 북마크 목록 조회",
            description = "고정된 1번 사용자의 공지 즐겨찾기 목록을 페이징하여 반환합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "조회 성공",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = NoticeSimpleResponse.class)))
                    )
            }
    )
    @PageableAsQueryParam
    public Page<NoticeSimpleResponse> myBookmarks(
            @ParameterObject Pageable pageable
    ) {
        return favoriteService.getMyBookmarks(pageable);
    }
}