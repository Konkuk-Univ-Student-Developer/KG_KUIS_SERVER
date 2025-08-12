package backend.kg_kuis_server.notice.exception;

import backend.kg_kuis_server.global.exception.ErrorMessage;
import backend.kg_kuis_server.global.exception.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum NoticeErrorCode implements ErrorResponse {

    NOT_FOUND(HttpStatus.NOT_FOUND, "해당 공지사항을 찾을 수 없습니다."),
    CATEGORY_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 카테고리가 존재하지 않습니다."),

    // 즐겨찾기 관련
    FAVORITE_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 즐겨찾기한 공지사항입니다."),
    FAVORITE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 즐겨찾기 내역이 존재하지 않습니다."),

    // 입력값/데이터 오류
    INVALID_NOTICE_ID(HttpStatus.BAD_REQUEST, "유효하지 않은 공지사항 ID입니다."),
    INVALID_CATEGORY_ID(HttpStatus.BAD_REQUEST, "유효하지 않은 카테고리 ID입니다."),

    // 서버 처리 실패
    FAVORITE_OPERATION_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "공지사항 즐겨찾기 처리에 실패했습니다."),
    NOTICE_LOAD_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "공지사항 데이터를 불러오지 못했습니다.");

    private final HttpStatus status;
    private final String message;

    @Override
    public ErrorMessage getErrorMessage() {
        return new ErrorMessage(this.message);
    }
}

