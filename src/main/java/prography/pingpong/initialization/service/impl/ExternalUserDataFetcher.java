package prography.pingpong.initialization.service.impl;

import java.util.List;
import prography.pingpong.initialization.dto.InitializeCommand;
import prography.pingpong.initialization.dto.UserData;

interface ExternalUserDataFetcher {

    List<UserData> fetch(InitializeCommand command);

}
