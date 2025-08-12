package backend.kg_kuis_server.graduation.exception;

import backend.kg_kuis_server.global.exception.ErrorMessage;
import backend.kg_kuis_server.global.exception.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum GraduationErrorCode implements ErrorResponse {
    NOT_FOUND(BAD_REQUEST, "해당 졸업 요건이 존재하지 않습니다.");

    private final HttpStatus status;
    private final String message;

    @Override
    public ErrorMessage getErrorMessage() {
        return new ErrorMessage(this.message);
    }
}
