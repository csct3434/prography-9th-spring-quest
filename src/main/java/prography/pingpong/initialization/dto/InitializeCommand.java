package prography.pingpong.initialization.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InitializeCommand {

    private final int seed;
    private final int quantity;

}
