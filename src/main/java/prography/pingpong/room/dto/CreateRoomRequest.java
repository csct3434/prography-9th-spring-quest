package prography.pingpong.room.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateRoomRequest {

    @NotNull
    private Integer userId;

    @NotBlank
    private String roomType;

    @NotNull
    @NotEmpty
    private String title;

    public CreateRoomCommand buildCommand() {
        return new CreateRoomCommand(userId, roomType, title);
    }
}
