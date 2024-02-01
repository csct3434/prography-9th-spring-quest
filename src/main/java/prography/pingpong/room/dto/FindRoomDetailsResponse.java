package prography.pingpong.room.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import prography.pingpong.common.util.DateTimeUtil;
import prography.pingpong.room.domain.room.Room;

@Getter
@AllArgsConstructor
public class FindRoomDetailsResponse {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private final int id;
    private final String title;
    private final int hostId;
    private final String roomType;
    private final String status;
    private final String createdAt;
    private final String updatedAt;

    public static FindRoomDetailsResponse build(Room room) {
        return new FindRoomDetailsResponse(
            room.getId(),
            room.getTitle(),
            room.getHost().getId(),
            room.getRoomType().name(),
            room.getStatus().name(),
            DateTimeUtil.format(room.getCreatedAt(), DATE_TIME_FORMAT),
            DateTimeUtil.format(room.getUpdatedAt(), DATE_TIME_FORMAT)
        );
    }
}
