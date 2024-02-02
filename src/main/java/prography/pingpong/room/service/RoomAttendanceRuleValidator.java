package prography.pingpong.room.service;

import prography.pingpong.room.domain.room.Room;
import prography.pingpong.user.domain.User;

public interface RoomAttendanceRuleValidator {

    boolean validate(User user, Room room);
}
