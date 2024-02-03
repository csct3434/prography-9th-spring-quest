package prography.pingpong.room.dto;

import lombok.Getter;
import prography.pingpong.common.util.IdValidator;

@Getter
public class FindRoomDetailsCommand {

    private final int roomId;

    public FindRoomDetailsCommand(int roomId) {
        IdValidator.validate(roomId);
        this.roomId = roomId;
    }
}
