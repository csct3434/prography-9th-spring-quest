package prography.pingpong.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import prography.pingpong.common.response.ApiResponse;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<ApiResponse<Void>> handleCustomException(RestApiException e) {
        log.info(e.getMessage());
        return buildResponse(e.getHttpStatus(), e.getApiResponse());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleUnexpectedException(Exception e) {
        log.error(e.getMessage());
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ApiResponse.error());
    }

    private ResponseEntity<ApiResponse<Void>> buildResponse(HttpStatus httpStatus, ApiResponse<Void> apiResponse) {
        return ResponseEntity.status(httpStatus)
            .body(apiResponse);
    }
}
