package prography.pingpong.room.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AttendRoomRequest {

    @NotNull
    private Integer userId;

    public AttendRoomCommand buildCommand(int roomId) {
        return new AttendRoomCommand(roomId, userId);
    }
}
