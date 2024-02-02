package prography.pingpong.initialization.service;

import java.util.List;
import prography.pingpong.initialization.dto.InitializeCommand;
import prography.pingpong.initialization.dto.UserData;

public interface ExternalUserDataFetcher {

    List<UserData> fetch(InitializeCommand command);

}
