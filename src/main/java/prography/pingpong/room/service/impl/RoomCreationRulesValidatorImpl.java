package prography.pingpong.room.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import prography.pingpong.room.domain.userroom.UserRoomRepository;
import prography.pingpong.room.service.RoomCreationRulesValidator;
import prography.pingpong.user.domain.User;

@Component
@RequiredArgsConstructor
public class RoomCreationRulesValidatorImpl implements RoomCreationRulesValidator {

    private final UserRoomRepository userRoomRepository;

    @Override
    public boolean validate(User host) {
        // Null Check
        if (host == null) {
            return false;
        }

        // 유저가 활성 상태가 아니면, 방을 생성할 수 없습니다.
        if (!host.isActive()) {
            return false;
        }

        // 유저가 다른 방에 참가한 상태라면, 방을 생성할 수 없습니다.
        if (userRoomRepository.existsByUser(host)) {
            return false;
        }

        return true;
    }
}
