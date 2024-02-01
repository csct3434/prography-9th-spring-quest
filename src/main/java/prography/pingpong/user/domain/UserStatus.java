package prography.pingpong.user.domain;

public enum UserStatus {
    WAIT, ACTIVE, NON_ACTIVE;

    private static final int ACTIVE_THRESHOLD = 30;
    private static final int WAIT_THRESHOLD = 60;

    public static UserStatus resolve(int fakerId) {
        if (fakerId <= ACTIVE_THRESHOLD) {
            return ACTIVE;
        } else if (fakerId <= WAIT_THRESHOLD) {
            return WAIT;
        } else {
            return NON_ACTIVE;
        }
    }
}
