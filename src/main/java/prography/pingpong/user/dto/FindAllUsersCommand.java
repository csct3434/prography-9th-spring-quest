package prography.pingpong.user.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import prography.pingpong.common.exception.RestApiException;

@Getter
public class FindAllUsersCommand {

    private final int pageSize;
    private final int pageNumber;

    public FindAllUsersCommand(int pageSize, int pageNumber) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        selfValidate();
    }

    private void selfValidate() {
        if (pageSize < 1 || pageNumber < 0) {
            throw RestApiException.badRequest(HttpStatus.BAD_REQUEST);
        }
    }
}
