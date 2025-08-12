package backend.kg_kuis_server.member.exception;

import backend.kg_kuis_server.global.exception.ErrorMessage;
import backend.kg_kuis_server.global.exception.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements ErrorResponse {
    NOT_FOUND(BAD_REQUEST, "해당 사용자가 존재하지 않습니다."),
    MAX_TRY_EXCEEDED(INTERNAL_SERVER_ERROR, "닉네임 생성에 실패했습니다."),
    NICK_NAME_READ_FAIL(INTERNAL_SERVER_ERROR, "닉네임 데이터 읽기를 실패했습니다."),
    NICK_NAME_ALREADY_EXIST(CONFLICT, "이미 사용중인 닉네임입니다."),
    NOT_ACADEMY_INFO_EXIST(CONFLICT, "사용자에 대한 상세 정보가 존재하지 않습니다");

    private final HttpStatus status;
    private final String message;

    @Override
    public ErrorMessage getErrorMessage() {
        return new ErrorMessage(this.message);
    }
}