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
import prography.pingpong.user.domain.User;
import prography.pingpong.user.domain.UserRepository;
import prography.pingpong.user.domain.UserStatus;

@Service
@RequiredArgsConstructor
public class CreateRoomServiceImpl implements CreateRoomService {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final UserRoomRepository userRoomRepository;

    @Override
    @Transactional
    public void doService(CreateRoomCommand command) {
        User host = userRepository.findByIdOrElseThrow(command.getUserId());

        checkException(host);

        Room room = roomRepository.save(Room.create(command, host));
        userRoomRepository.save(UserRoom.buildRed(host, room));
    }

    private void checkException(User host) {
        // 활성 상태가 아니거나, 다른 방에 참여한 경우 201 응답
        if (host.getStatus() != UserStatus.ACTIVE || userRoomRepository.existsByUser(host)) {
            throw RestApiException.badRequest();
        }
    }
}
