package prography.pingpong.room.dto;

import lombok.Getter;
import prography.pingpong.common.util.IdValidator;

@Getter
public class AttendRoomCommand {

    private final int roomId;
    private final int userId;

    public AttendRoomCommand(int roomId, int userId) {
        IdValidator.validate(roomId, userId);
        this.roomId = roomId;
        this.userId = userId;
    }
}
