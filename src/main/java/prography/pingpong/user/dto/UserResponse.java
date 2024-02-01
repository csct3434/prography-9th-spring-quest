package prography.pingpong.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import prography.pingpong.common.util.DateTimeUtil;
import prography.pingpong.user.domain.User;

@Getter
@AllArgsConstructor
public class UserResponse {

    private final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private final int id;
    private final int fakerId;
    private final String name;
    private final String email;
    private final String status;
    private final String createdAt;
    private final String updatedAt;

    public static UserResponse build(User user) {
        return new UserResponse(
            user.getId(),
            user.getFakerId(),
            user.getName(),
            user.getEmail(),
            user.getStatus().name(),
            DateTimeUtil.format(user.getCreatedAt(), DATE_TIME_FORMAT),
            DateTimeUtil.format(user.getUpdatedAt(), DATE_TIME_FORMAT));
    }
}
