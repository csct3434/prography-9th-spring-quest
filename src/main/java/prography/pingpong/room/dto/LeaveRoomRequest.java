package prography.pingpong.room.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LeaveRoomRequest {

    @NotNull
    private Integer userId;

    public LeaveRoomCommand buildCommand(int roomId) {
        return new LeaveRoomCommand(roomId, userId);
    }
}
