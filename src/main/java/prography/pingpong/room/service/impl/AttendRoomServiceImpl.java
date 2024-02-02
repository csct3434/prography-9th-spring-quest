package prography.pingpong.room.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prography.pingpong.common.exception.RestApiException;
import prography.pingpong.room.domain.room.Room;
import prography.pingpong.room.domain.room.RoomRepository;
import prography.pingpong.room.domain.userroom.Team;
import prography.pingpong.room.domain.userroom.UserRoom;
import prography.pingpong.room.domain.userroom.UserRoomRepository;
import prography.pingpong.room.dto.AttendRoomCommand;
import prography.pingpong.room.service.AttendRoomService;
import prography.pingpong.room.service.RoomAttendanceRuleValidator;
import prography.pingpong.user.domain.User;
import prography.pingpong.user.domain.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class AttendRoomServiceImpl implements AttendRoomService {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final UserRoomRepository userRoomRepository;
    private final RoomAttendanceRuleValidator roomAttendanceRuleValidator;

    @Override
    public void doService(AttendRoomCommand command) {
        User user = userRepository.findByIdOrElseThrow(command.getUserId());
        Room room = roomRepository.findByIdOrElseThrow(command.getRoomId());

        if(!roomAttendanceRuleValidator.validate(user, room)) {
            throw RestApiException.badRequest();
        }

        Team userTeam = resolveAvailableTeam(room);

        userRoomRepository.save(UserRoom.build(user, room, userTeam));
    }

    private Team resolveAvailableTeam(Room room) {
        int teamCapacity = room.getTeamCapacity();
        int redTeamCount = userRoomRepository.countByRoomAndTeam(room, Team.RED);

        // 레드팀에 자리가 비었다면, 레드팀으로 자동 배정됩니다.
        return redTeamCount < teamCapacity ? Team.RED : Team.BLUE;
    }
}
