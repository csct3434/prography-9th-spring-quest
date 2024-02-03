package prography.pingpong.room.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prography.pingpong.common.exception.RestApiException;
import prography.pingpong.room.domain.room.Room;
import prography.pingpong.room.domain.room.RoomRepository;
import prography.pingpong.room.domain.userroom.UserRoom;
import prography.pingpong.room.domain.userroom.UserRoomRepository;
import prography.pingpong.room.dto.CreateRoomCommand;
import prography.pingpong.room.service.CreateRoomService;
import prography.pingpong.room.service.RoomCreationRulesValidator;
import prography.pingpong.user.domain.User;
import prography.pingpong.user.domain.UserRepository;

@Service
@RequiredArgsConstructor
public class CreateRoomServiceImpl implements CreateRoomService {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final UserRoomRepository userRoomRepository;
    private final RoomCreationRulesValidator roomCreationRulesValidator;

    @Override
    @Transactional
    public void doService(CreateRoomCommand command) {
        User host = userRepository.findByIdOrElseThrow(command.getUserId());

        validateRoomCreationRules(host);

        Room room = roomRepository.save(Room.create(command, host));
        userRoomRepository.save(UserRoom.buildRed(host, room));
    }

    private void validateRoomCreationRules(User host) {
        if (!roomCreationRulesValidator.validate(host)) {
            throw RestApiException.badRequest();
        }
    }
}
