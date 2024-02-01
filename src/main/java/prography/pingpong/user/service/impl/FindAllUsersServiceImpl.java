package prography.pingpong.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prography.pingpong.user.domain.User;
import prography.pingpong.user.domain.UserRepository;
import prography.pingpong.user.dto.FindAllUsersCommand;
import prography.pingpong.user.dto.FindAllUsersResponse;
import prography.pingpong.user.service.FindAllUsersService;

@Service
@RequiredArgsConstructor
public class FindAllUsersServiceImpl implements FindAllUsersService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public FindAllUsersResponse doService(FindAllUsersCommand command) {
        Pageable pageable = PageRequest.of(command.getPageNumber(), command.getPageSize());
        Page<User> page = userRepository.findAllByOrderByIdAsc(pageable);
        return FindAllUsersResponse.build(page);
    }
}
