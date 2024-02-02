package prography.pingpong.initialization.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InitializeRequest {

    @NotNull
    private Integer seed;

    @NotNull
    private Integer quantity;

    public InitializeCommand buildCommand() {
        return new InitializeCommand(seed, quantity);
    }
}
