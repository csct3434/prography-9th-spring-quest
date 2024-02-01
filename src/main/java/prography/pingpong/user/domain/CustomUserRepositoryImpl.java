package prography.pingpong.user.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import prography.pingpong.common.exception.RestApiException;

@Repository
@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository {

    private final EntityManager entityManager;

    @Override
    public User findByIdOrElseThrow(Integer id) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class);
        query.setParameter("id", id);

        List<User> resultList = query.getResultList();

        if (resultList.isEmpty()) {
            throw RestApiException.badRequest();
        }
        return resultList.get(0);
    }
}
