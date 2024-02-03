package prography.pingpong.room.domain.userroom;

import prography.pingpong.user.domain.User;

public interface CustomUserRoomRepository {

    UserRoom findByUserOrElseThrow(User user);

}
