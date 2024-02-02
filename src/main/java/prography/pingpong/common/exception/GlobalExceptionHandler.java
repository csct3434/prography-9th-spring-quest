package prography.pingpong.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import prography.pingpong.common.response.ApiResponse;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<ApiResponse<Void>> handleRestApiException(RestApiException e) {
        log.info(e.getMessage());
        return buildResponse(e.getApiResponse());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleUnexpectedException(Exception e) {
        log.error(e.getMessage());
        return buildResponse(ApiResponse.error());
    }

    private ResponseEntity<ApiResponse<Void>> buildResponse(ApiResponse<Void> apiResponse) {
        return ResponseEntity.ok(apiResponse);
    }
}
