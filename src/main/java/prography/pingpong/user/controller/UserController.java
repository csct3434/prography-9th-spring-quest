package prography.pingpong.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import prography.pingpong.common.response.ApiResponse;
import prography.pingpong.user.dto.FindAllUsersCommand;
import prography.pingpong.user.dto.FindAllUsersResponse;
import prography.pingpong.user.service.FindAllUsersService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final FindAllUsersService findAllUsersService;

    @GetMapping
    public ResponseEntity<ApiResponse<FindAllUsersResponse>> findAllUsers(
        @RequestParam("size") Integer pageSize,
        @RequestParam("page") Integer pageNumber
    ) {
        FindAllUsersCommand command = new FindAllUsersCommand(pageSize, pageNumber);
        FindAllUsersResponse result = findAllUsersService.doService(command);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

}
