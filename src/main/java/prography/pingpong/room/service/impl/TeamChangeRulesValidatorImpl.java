package prography.pingpong.room.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import prography.pingpong.room.domain.room.Room;
import prography.pingpong.room.domain.room.RoomStatus;
import prography.pingpong.room.domain.userroom.UserRoom;
import prography.pingpong.room.domain.userroom.UserRoomRepository;
import prography.pingpong.room.service.TeamChangeRulesValidator;

@Component
@RequiredArgsConstructor
public class TeamChangeRulesValidatorImpl implements TeamChangeRulesValidator {

    private final UserRoomRepository userRoomRepository;

    @Override
    public boolean validate(UserRoom userRoom, Room room) {
        // Null Check
        if (userRoom == null || room == null) {
            return false;
        }

        // 현재 방이 대기(WAIT) 상태가 아니라면, 팀을 변경할 수 없습니다.
        if (room.getStatus() != RoomStatus.WAIT) {
            return false;
        }

        // 반대편 팀이 가득 찼다면, 팀을 변경할 수 없습니다.
        int oppositeTeamCount = userRoomRepository.countByRoomAndTeam(room, userRoom.getOppositeTeam());
        if (oppositeTeamCount == room.getTeamCapacity()) {
            return false;
        }

        return true;
    }
}
