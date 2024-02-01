package prography.pingpong.room.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateRoomRequest {

    @NotNull
    @Positive
    private Integer userId;

    @NotNull
    @NotBlank
    private String roomType;

    @NotNull
    @NotBlank
    private String title;

    public CreateRoomCommand toCommand() {
        return new CreateRoomCommand(userId, roomType, title);
    }
}
