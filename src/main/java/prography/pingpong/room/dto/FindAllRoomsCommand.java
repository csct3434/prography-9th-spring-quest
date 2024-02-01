package prography.pingpong.room.dto;

import lombok.Getter;
import prography.pingpong.common.exception.RestApiException;

@Getter
public class FindAllRoomsCommand {

    private final int pageSize;
    private final int pageNumber;

    public FindAllRoomsCommand(int pageSize, int pageNumber) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        selfValidate();
    }

    private void selfValidate() {
        if (pageSize < 1 || pageNumber < 0) {
            throw RestApiException.badRequest();
        }
    }
}
