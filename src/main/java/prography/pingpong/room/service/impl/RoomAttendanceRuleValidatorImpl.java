package prography.pingpong.room.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import prography.pingpong.room.domain.room.Room;
import prography.pingpong.room.domain.room.RoomStatus;
import prography.pingpong.room.domain.userroom.UserRoomRepository;
import prography.pingpong.room.service.RoomAttendanceRuleValidator;
import prography.pingpong.user.domain.User;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoomAttendanceRuleValidatorImpl implements RoomAttendanceRuleValidator {

    private final UserRoomRepository userRoomRepository;

    @Override
    public boolean validate(User user, Room room) {
        // Null Check
        if (user == null || room == null) {
            return false;
        }

        // Rule-1. 방이 대기(WAIT) 상태가 아니라면, 참가할 수 없습니다.
        if (room.getStatus() != RoomStatus.WAIT) {
            return false;
        }

        // Rule-2. 활성 유저가 아니라면, 참가할 수 없습니다.
        if (!user.isActive()) {
            return false;
        }

        // Rule-3. 유저가 현재 참여한 방이 있다면, 참가할 수 없습니다.
        if (userRoomRepository.existsByUser(user)) {
            return false;
        }

        // Rule-4. 방의 정원이 초과됐다면, 참가할 수 없습니다.
        int attendance = userRoomRepository.countByRoom(room);
        if (attendance >= room.getCapacity()) {
            return false;
        }

        return true;
    }
}
