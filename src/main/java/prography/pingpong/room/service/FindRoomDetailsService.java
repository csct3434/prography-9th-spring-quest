package prography.pingpong.room.service;

import prography.pingpong.room.dto.FindRoomDetailsResponse;
import prography.pingpong.room.dto.FindRoomServiceCommand;

public interface FindRoomDetailsService {

    FindRoomDetailsResponse doService(FindRoomServiceCommand command);

}
