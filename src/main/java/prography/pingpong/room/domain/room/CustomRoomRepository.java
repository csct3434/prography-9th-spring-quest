package prography.pingpong.room.domain.room;

public interface CustomRoomRepository {

    Room findByIdOrElseThrow(Integer id);
}
