package prography.pingpong.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import prography.pingpong.common.response.ApiResponse;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RestApiException extends RuntimeException {

    private final ApiResponse<Void> apiResponse;

    public static RestApiException badRequest() {
        return new RestApiException(ApiResponse.badRequest());
    }
}
