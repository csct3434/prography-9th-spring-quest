package prography.pingpong.room.dto;

import lombok.Getter;
import prography.pingpong.common.util.IdValidator;

@Getter
public class ChangeTeamCommand {

    private final int roomId;
    private final int userId;

    public ChangeTeamCommand(int roomId, int userId) {
        IdValidator.validate(roomId, userId);
        this.roomId = roomId;
        this.userId = userId;
    }
}
