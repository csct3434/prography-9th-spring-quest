package prography.pingpong.room.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import prography.pingpong.common.exception.RestApiException;

@Getter
public class FindRoomServiceCommand {

    private final int roomId;

    public FindRoomServiceCommand(int roomId) {
        this.roomId = roomId;
        selfValidate();
    }

    private void selfValidate() {
        if (roomId < 1) {
            throw RestApiException.badRequest(HttpStatus.BAD_REQUEST);
        }
    }
}
