package prography.pingpong.room.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import prography.pingpong.room.domain.room.Room;

@Getter
@AllArgsConstructor
public class FindAllRoomsResponse {

    private final int totalElements;
    private final int totalPages;
    private final List<RoomResponse> roomList;

    public static FindAllRoomsResponse build(Page<Room> page) {
        List<RoomResponse> roomList = page.getContent()
            .stream()
            .map(RoomResponse::build)
            .toList();

        return new FindAllRoomsResponse(
            (int) page.getTotalElements(),
            page.getTotalPages(),
            roomList);
    }
}
