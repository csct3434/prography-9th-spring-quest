package prography.pingpong.room.service;

import prography.pingpong.room.dto.ChangeTeamCommand;

public interface ChangeTeamService {

    void doService(ChangeTeamCommand command);

}
