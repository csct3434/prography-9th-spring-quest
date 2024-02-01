package prography.pingpong.user.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import prography.pingpong.user.domain.User;

@Getter
@AllArgsConstructor
public class FindAllUsersResponse {

    private final int totalElements;
    private final int totalPages;
    private final List<UserResponse> userList;

    public static FindAllUsersResponse build(Page<User> page) {
        List<UserResponse> userList = page.getContent()
            .stream()
            .map(UserResponse::build)
            .toList();

        return new FindAllUsersResponse(
            (int) page.getTotalElements(),
            page.getTotalPages(),
            userList);
    }
}
