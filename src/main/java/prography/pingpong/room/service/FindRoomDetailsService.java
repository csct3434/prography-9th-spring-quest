package prography.pingpong.room.service;

import prography.pingpong.room.dto.FindRoomDetailsResponse;
import prography.pingpong.room.dto.FindRoomDetailsCommand;

public interface FindRoomDetailsService {

    FindRoomDetailsResponse doService(FindRoomDetailsCommand command);

}
