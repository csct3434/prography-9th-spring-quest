package prography.pingpong.user.service;

import prography.pingpong.user.dto.FindAllUsersCommand;
import prography.pingpong.user.dto.FindAllUsersResponse;

public interface FindAllUsersService {

    FindAllUsersResponse doService(FindAllUsersCommand command);

}
