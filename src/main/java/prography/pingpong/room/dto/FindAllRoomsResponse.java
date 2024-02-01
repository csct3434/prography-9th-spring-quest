package prography.pingpong.room.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindAllRoomsResponse {

    private final int totelElements;
    private final int totalPages;
    private final List<RoomResponse> roomList;

}
