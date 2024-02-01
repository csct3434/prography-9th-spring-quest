package prography.pingpong.initialization.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

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

}
