package prography.pingpong.room.domain.userroom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prography.pingpong.room.domain.room.Room;
import prography.pingpong.user.domain.User;

@Repository
public interface UserRoomRepository extends JpaRepository<UserRoom, Integer>, CustomUserRoomRepository {

    boolean existsByUser(User user);

    int countByRoom(Room room);

    int countByRoomAndTeam(Room room, Team team);

    void deleteByRoom(Room room);

    void deleteByUser(User user);

}
