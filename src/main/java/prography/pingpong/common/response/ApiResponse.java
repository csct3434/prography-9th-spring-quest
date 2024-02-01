package prography.pingpong.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse<T> {

    private Integer code;
    private String message;
    private T result;

    public static <T> ApiResponse<T> success(T result) {
        return new ApiResponse<>(200, "API 요청이 성공했습니다.", result);
    }

    public static ApiResponse<Void> success() {
        return new ApiResponse<>(200, "API 요청이 성공했습니다.", null);
    }

    public static ApiResponse<Void> badRequest() {
        return new ApiResponse<>(201, "불가능한 요청입니다.", null);
    }

    public static ApiResponse<Void> error() {
        return new ApiResponse<>(500, "에러가 발생했습니다.", null);
    }
}
