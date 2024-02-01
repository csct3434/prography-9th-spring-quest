package prography.pingpong.room.service;

import prography.pingpong.room.dto.FindAllRoomsCommand;
import prography.pingpong.room.dto.FindAllRoomsResponse;

public interface FindAllRoomsService {

    FindAllRoomsResponse doService(FindAllRoomsCommand command);

}
