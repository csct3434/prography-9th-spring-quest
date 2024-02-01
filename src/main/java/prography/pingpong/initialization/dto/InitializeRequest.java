package prography.pingpong.initialization.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InitializeRequest {

    private int seed;
    private int quantity;

    public InitializeCommand toCommand() {
        return new InitializeCommand(seed, quantity);
    }
}
