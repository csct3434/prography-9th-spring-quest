package prography.pingpong.room.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import prography.pingpong.room.domain.room.Room;
import prography.pingpong.room.domain.room.RoomStatus;
import prography.pingpong.room.domain.userroom.UserRoomRepository;
import prography.pingpong.room.service.GameStartRulesValidator;
import prography.pingpong.user.domain.User;

@Component
@RequiredArgsConstructor
public class GameStartRulesValidatorImpl implements GameStartRulesValidator {

    private final UserRoomRepository userRoomRepository;

    @Override
    @Transactional(readOnly = true)
    public boolean validate(User user, Room room) {
        // 호스트가 아니라면 게임을 시작할 수 없습니다.
        if (!user.equals(room.getHost())) {
            return false;
        }

        // 방이 대기 상태가 아니라면, 게임을 시작할 수 없습니다.
        if (room.getStatus() != RoomStatus.WAIT) {
            return false;
        }

        // 방 정원에 도달하지 않았다면, 게임을 시작할 수 없습니다.
        int attendance = userRoomRepository.countByRoom(room);
        if (attendance != room.getCapacity()) {
            return false;
        }

        return true;
    }
}
