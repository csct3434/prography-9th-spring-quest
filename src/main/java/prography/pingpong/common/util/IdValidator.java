package prography.pingpong.common.util;

import java.util.Arrays;
import prography.pingpong.common.exception.RestApiException;

public class IdValidator {

    public static void validate(int... ids) {
        if (Arrays.stream(ids).anyMatch(id -> id < 1)) {
            throw RestApiException.badRequest();
        }
    }
}
