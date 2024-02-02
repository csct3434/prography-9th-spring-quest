package prography.pingpong.room.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prography.pingpong.common.exception.RestApiException;
import prography.pingpong.room.domain.room.Room;
import prography.pingpong.room.domain.room.RoomRepository;
import prography.pingpong.room.domain.userroom.UserRoomRepository;
import prography.pingpong.room.dto.LeaveRoomCommand;
import prography.pingpong.room.service.LeaveRoomService;
import prography.pingpong.room.service.RoomLeaveRulesValidator;
import prography.pingpong.user.domain.User;
import prography.pingpong.user.domain.UserRepository;

@Service
@RequiredArgsConstructor
public class LeaveRoomServiceImpl implements LeaveRoomService {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final UserRoomRepository userRoomRepository;
    private final RoomLeaveRulesValidator roomLeaveRulesValidator;

    @Override
    @Transactional
    public void doService(LeaveRoomCommand command) {
        User user = userRepository.findByIdOrElseThrow(command.getUserId());
        Room room = roomRepository.findByIdOrElseThrow(command.getRoomId());

        validateRoomLeaveRules(user, room);

        userRoomRepository.deleteByUser(user);

        finishRoomIfHostLeft(user, room);
    }

    private void validateRoomLeaveRules(User user, Room room) {
        if (!roomLeaveRulesValidator.validate(user, room)) {
            throw RestApiException.badRequest();
        }
    }

    // 호스트가 방을 나가게 되면, 방에 있던 모든 사람도 해당 방에서 나가게 됩니다.
    // 또한, 방은 끝난(FINISH) 상태가 됩니다.
    private void finishRoomIfHostLeft(User user, Room room) {
        if (user.equals(room.getHost())) {
            userRoomRepository.deleteByRoom(room);
            room.setFinish();
        }
    }
}
