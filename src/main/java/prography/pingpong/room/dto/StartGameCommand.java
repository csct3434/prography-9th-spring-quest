package prography.pingpong.room.dto;

import lombok.Getter;
import prography.pingpong.common.util.IdValidator;

@Getter
public class StartGameCommand {

    private final int roomId;
    private final int userId;

    public StartGameCommand(int roomId, int userId) {
        IdValidator.validate(roomId, userId);
        this.roomId = roomId;
        this.userId = userId;
    }
}
