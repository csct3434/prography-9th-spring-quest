package prography.pingpong.room.service.impl;

import static java.lang.Thread.sleep;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import prography.pingpong.room.domain.room.Room;
import prography.pingpong.room.domain.room.RoomRepository;
import prography.pingpong.room.domain.userroom.UserRoomRepository;
import prography.pingpong.room.service.FinishRoomWhenTimeOverAsync;

@Component
@RequiredArgsConstructor
public class FinishRoomWhenTimeOverAsyncImpl implements FinishRoomWhenTimeOverAsync {

    private final RoomRepository roomRepository;
    private final UserRoomRepository userRoomRepository;

    private static final int TIME_OVER_IN_SECONDS = 60;

    @Async
    @Override
    @Transactional
    public void finish(int roomId) {
        waitUntilTimeOver();

        Room room = roomRepository.findByIdOrElseThrow(roomId);
        userRoomRepository.deleteByRoom(room);
        room.setFinish();
    }

    private void waitUntilTimeOver() {
        try {
            sleep(TimeUnit.SECONDS.toMillis(TIME_OVER_IN_SECONDS));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
