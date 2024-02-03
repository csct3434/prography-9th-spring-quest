package prography.pingpong.room.domain.userroom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import prography.pingpong.common.exception.RestApiException;
import prography.pingpong.user.domain.User;

@Repository
@RequiredArgsConstructor
public class CustomUserRoomRepositoryImpl implements CustomUserRoomRepository {

    private final EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public UserRoom findByUserOrElseThrow(User user) {
        TypedQuery<UserRoom> query = entityManager.createQuery("SELECT u FROM UserRoom u WHERE u.user = :user",
            UserRoom.class);
        query.setParameter("user", user);

        List<UserRoom> resultList = query.getResultList();

        if (resultList.isEmpty()) {
            throw RestApiException.badRequest();
        }
        return resultList.get(0);
    }
}
