package prography.pingpong.room.domain.room;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import prography.pingpong.common.exception.RestApiException;

@Repository
@RequiredArgsConstructor
public class CustomRoomRepositoryImpl implements CustomRoomRepository{

    private final EntityManager entityManager;

    @Override
    public Room findByIdOrElseThrow(Integer id) {
        TypedQuery<Room> query = entityManager.createQuery("SELECT r FROM Room r WHERE r.id = :id", Room.class);
        query.setParameter("id", id);

        List<Room> resultList = query.getResultList();

        if (resultList.isEmpty()) {
            throw RestApiException.badRequest();
        }
        return resultList.get(0);
    }
}
