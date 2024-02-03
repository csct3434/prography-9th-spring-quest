package prography.pingpong.room.service;

import prography.pingpong.room.domain.room.Room;
import prography.pingpong.room.domain.userroom.UserRoom;

public interface TeamChangeRulesValidator {

    boolean validate(UserRoom userRoom, Room room);

}
