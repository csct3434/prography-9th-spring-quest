package prography.pingpong.room.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangeTeamRequest {

    @NotNull
    private Integer userId;

    public ChangeTeamCommand buildCommand(int roomId) {
        return new ChangeTeamCommand(roomId, userId);
    }
}
