package prography.pingpong.room.dto;

import lombok.Getter;
import prography.pingpong.common.exception.RestApiException;

@Getter
public class ChangeTeamCommand {

    private final int roomId;
    private final int userId;

    public ChangeTeamCommand(int roomId, int userId) {
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