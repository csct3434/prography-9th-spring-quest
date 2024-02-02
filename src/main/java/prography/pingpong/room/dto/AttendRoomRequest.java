package prography.pingpong.room.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AttendRoomRequest {

    private Integer userId;

    public AttendRoomCommand toCommand(int roomId) {
        return new AttendRoomCommand(roomId, userId);
    }
}
