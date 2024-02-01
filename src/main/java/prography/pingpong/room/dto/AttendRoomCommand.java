package prography.pingpong.room.dto;

import lombok.Getter;
import prography.pingpong.common.exception.RestApiException;

@Getter
public class AttendRoomCommand {

    private final int roomId;
    private final int userId;

    public AttendRoomCommand(int roomId, int userId) {
        this.roomId = roomId;
        this.userId = userId;
        selfValidate();
    }

    private void selfValidate() {
        if (roomId < 1 || userId < 1) {
            throw RestApiException.badRequest();
        }
    }
}
