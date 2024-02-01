package prography.pingpong.room.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prography.pingpong.room.domain.room.Room;
import prography.pingpong.room.domain.room.RoomRepository;
import prography.pingpong.room.dto.FindRoomDetailsResponse;
import prography.pingpong.room.dto.FindRoomServiceCommand;
import prography.pingpong.room.service.FindRoomDetailsService;

@Service
@RequiredArgsConstructor
public class FindRoomDetailsServiceImpl implements FindRoomDetailsService {

    private final RoomRepository roomRepository;

    @Override
    @Transactional(readOnly = true)
    public FindRoomDetailsResponse doService(FindRoomServiceCommand command) {
        Room room = roomRepository.findByIdOrElseThrow(command.getRoomId());
        return FindRoomDetailsResponse.build(room);
    }
}
