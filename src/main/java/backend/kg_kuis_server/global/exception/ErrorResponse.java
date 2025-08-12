package backend.kg_kuis_server.global.exception;

import org.springframework.http.HttpStatus;

public interface ErrorResponse {
    HttpStatus getStatus();
    ErrorMessage getErrorMessage();
}
