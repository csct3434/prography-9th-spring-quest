package prography.pingpong.room.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import prography.pingpong.room.domain.room.Room;
import prography.pingpong.room.domain.room.RoomStatus;
import prography.pingpong.room.domain.userroom.UserRoomRepository;
import prography.pingpong.room.service.RoomLeaveRulesValidator;
import prography.pingpong.user.domain.User;

@Component
@RequiredArgsConstructor
public class RoomLeaveRulesValidatorImpl implements RoomLeaveRulesValidator {

    private final UserRoomRepository userRoomRepository;

    @Override
    public boolean validate(User user, Room room) {
        // 유저가 해당 방에 참가하지 않았다면 나갈 수 없습니다.
        if (!userRoomRepository.existsByUser(user)) {
            return false;
        }

        // 시작(PROGRESS) 상태이거나 끝난(FINISH) 상태의 방은 나갈 수 없습니다.
        if (room.getStatus() == RoomStatus.PROGRESS || room.getStatus() == RoomStatus.FINISH) {
            return false;
        }

        return true;
    }
}
