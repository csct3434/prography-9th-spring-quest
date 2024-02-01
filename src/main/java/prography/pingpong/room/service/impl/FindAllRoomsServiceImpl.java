package prography.pingpong.room.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prography.pingpong.room.domain.room.Room;
import prography.pingpong.room.domain.room.RoomRepository;
import prography.pingpong.room.dto.FindAllRoomsCommand;
import prography.pingpong.room.dto.FindAllRoomsResponse;
import prography.pingpong.room.service.FindAllRoomsService;

@Service
@RequiredArgsConstructor
public class FindAllRoomsServiceImpl implements FindAllRoomsService {

    private final RoomRepository roomRepository;

    @Override
    @Transactional(readOnly = true)
    public FindAllRoomsResponse doService(FindAllRoomsCommand command) {
        PageRequest pageRequest = PageRequest.of(command.getPageNumber(), command.getPageSize());
        Page<Room> page = roomRepository.findAllByOrderByIdAsc(pageRequest);
        return FindAllRoomsResponse.build(page);
    }
}
