package prography.pingpong.room.dto;

import lombok.Getter;
import prography.pingpong.common.exception.RestApiException;
import prography.pingpong.room.domain.room.RoomType;

@Getter
public class CreateRoomCommand {

    private final int userId;
    private final RoomType roomType;
    private final String title;

    public CreateRoomCommand(int userId, String roomType, String title) {
        this.userId = userId;
        this.title = title;

        // 일치하는 RoomType이 없으면 201 응답
        try {
            this.roomType = RoomType.valueOf(roomType);
        } catch (Exception e) {
            throw RestApiException.badRequest();
        }
    }
}
