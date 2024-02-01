package prography.pingpong.room.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import prography.pingpong.room.domain.room.Room;

@Getter
@AllArgsConstructor
public class RoomResponse {

    private final int id;
    private final String title;
    private final int hostId;
    private final String roomType;
    private final String status;

    public static RoomResponse build(Room room) {
        return new RoomResponse(
            room.getId(),
            room.getTitle(),
            room.getHost().getId(),
            room.getRoomType().name(),
            room.getStatus().name());
    }
}
