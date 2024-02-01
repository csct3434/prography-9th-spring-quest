package prography.pingpong.room.domain.userroom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prography.pingpong.user.domain.User;

@Repository
public interface UserRoomRepository extends JpaRepository<UserRoom, Integer> {

    boolean existsByUser(User user);

}