package prography.pingpong.room.service;

import prography.pingpong.user.domain.User;

public interface RoomCreationRulesValidator {

    boolean validate(User user);
}
