package prography.pingpong.room.service;

import prography.pingpong.room.dto.LeaveRoomCommand;

public interface LeaveRoomService {

    void doService(LeaveRoomCommand command);
}
