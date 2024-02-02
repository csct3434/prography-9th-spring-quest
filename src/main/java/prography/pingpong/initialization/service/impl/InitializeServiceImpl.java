package prography.pingpong.initialization.service.impl;

import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prography.pingpong.initialization.dto.InitializeCommand;
import prography.pingpong.initialization.dto.UserData;
import prography.pingpong.initialization.service.ExternalUserDataFetcher;
import prography.pingpong.initialization.service.InitializeService;
import prography.pingpong.initialization.service.TableInitializer;
import prography.pingpong.user.domain.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class InitializeServiceImpl implements InitializeService {

    private final TableInitializer tableInitializer;
    private final ExternalUserDataFetcher externalUserDataFetcher;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void doService(InitializeCommand command) {
        tableInitializer.init();

        List<UserData> userDataList = externalUserDataFetcher.fetch(command);

        userDataList.stream()
            .sorted(Comparator.comparingInt(UserData::getFakerId))
            .map(UserData::buildUser)
            .forEach(userRepository::save);
    }
}
