package prography.pingpong.user.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindAllUsersResponse {

    private final int totalElements;
    private final int totalPages;
    private final List<UserResponse> userList;

}
