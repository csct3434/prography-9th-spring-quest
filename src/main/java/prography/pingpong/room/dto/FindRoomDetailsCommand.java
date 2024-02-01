package prography.pingpong.room.dto;

import lombok.Getter;
import prography.pingpong.common.exception.RestApiException;

@Getter
public class FindRoomDetailsCommand {

    private final int roomId;

    public FindRoomDetailsCommand(int roomId) {
        this.roomId = roomId;
        selfValidate();
    }

    private void selfValidate() {
        if (roomId < 1) {
            throw RestApiException.badRequest();
        }
    }
}
