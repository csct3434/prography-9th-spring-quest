package prography.pingpong.room.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prography.pingpong.common.exception.RestApiException;
import prography.pingpong.room.domain.room.Room;
import prography.pingpong.room.domain.room.RoomRepository;
import prography.pingpong.room.domain.userroom.UserRoom;
import prography.pingpong.room.domain.userroom.UserRoomRepository;
import prography.pingpong.room.dto.ChangeTeamCommand;
import prography.pingpong.room.service.ChangeTeamService;
import prography.pingpong.room.service.TeamChangeRulesValidator;
import prography.pingpong.user.domain.User;
import prography.pingpong.user.domain.UserRepository;

@Service
@RequiredArgsConstructor
public class ChangeTeamServiceImpl implements ChangeTeamService {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final UserRoomRepository userRoomRepository;
    private final TeamChangeRulesValidator teamChangeRulesValidator;

    @Override
    @Transactional
    public void doService(ChangeTeamCommand command) {
        User user = userRepository.findByIdOrElseThrow(command.getUserId());
        Room room = roomRepository.findByIdOrElseThrow(command.getRoomId());
        UserRoom userRoom = userRoomRepository.findByUserOrElseThrow(user);

        validateTeamChangeRules(userRoom, room);

        userRoom.changeTeam();
    }

    private void validateTeamChangeRules(UserRoom userRoom, Room room) {
        if (!teamChangeRulesValidator.validate(userRoom, room)) {
            throw RestApiException.badRequest();
        }
    }
}
