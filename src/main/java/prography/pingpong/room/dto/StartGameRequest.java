package prography.pingpong.room.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StartGameRequest {

    @NotNull
    private Integer userId;

    public StartGameCommand buildCommand(int roomId) {
        return new StartGameCommand(roomId, userId);
    }
}
