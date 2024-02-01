package prography.pingpong.user.domain;

public interface CustomUserRepository {

    User findByIdOrElseThrow(Integer id);
}
