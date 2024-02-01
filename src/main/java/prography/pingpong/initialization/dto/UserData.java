package prography.pingpong.initialization.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import prography.pingpong.user.domain.User;
import prography.pingpong.user.domain.UserStatus;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserData {

    @JsonProperty(value = "id")
    private Integer fakerId;

    @JsonProperty(value = "username")
    private String name;

    @JsonProperty(value = "email")
    private String email;

    public User buildUser() {
        return new User(fakerId, name, email, UserStatus.resolve(fakerId));
    }
}
