package prography.pingpong.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import prography.pingpong.common.response.ApiResponse;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RestApiException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final ApiResponse<Void> apiResponse;

    public static RestApiException badRequest(HttpStatus httpStatus) {
        return new RestApiException(httpStatus, ApiResponse.badRequest());
    }
}
