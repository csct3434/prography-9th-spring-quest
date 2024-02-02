package prography.pingpong.room.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prography.pingpong.common.exception.RestApiException;
import prography.pingpong.room.domain.room.Room;
import prography.pingpong.room.domain.room.RoomRepository;
import prography.pingpong.room.dto.StartGameCommand;
import prography.pingpong.room.service.FinishRoomWhenTimeOverAsync;
import prography.pingpong.room.service.GameStartRulesValidator;
import prography.pingpong.room.service.StartGameService;
import prography.pingpong.user.domain.User;
import prography.pingpong.user.domain.UserRepository;

@Service
@RequiredArgsConstructor
public class StartGameServiceImpl implements StartGameService {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final GameStartRulesValidator gameStartRulesValidator;
    private final FinishRoomWhenTimeOverAsync finishRoomWhenTimeOverAsync;

    @Override
    @Transactional
    public void doService(StartGameCommand command) {
        User user = userRepository.findByIdOrElseThrow(command.getUserId());
        Room room = roomRepository.findByIdOrElseThrow(command.getRoomId());

        validateGameStartRules(user, room);

        room.setProgress();

        finishRoomWhenTimeOverAsync.finish(room.getId());
    }

    private void validateGameStartRules(User user, Room room) {
        if (!gameStartRulesValidator.validate(user, room)) {
            throw RestApiException.badRequest();
        }
    }
}
