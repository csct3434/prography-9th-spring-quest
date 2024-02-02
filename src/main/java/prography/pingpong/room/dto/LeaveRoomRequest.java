package prography.pingpong.room.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LeaveRoomRequest {

    private Integer userId;

    public LeaveRoomCommand buildCommand(int roomId) {
        return new LeaveRoomCommand(roomId, userId);
    }
}
