package prography.pingpong.initialization.service.impl;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import prography.pingpong.initialization.service.TableInitializer;

@Component
@Transactional
@RequiredArgsConstructor
public class TableInitializerImpl implements TableInitializer {

    private final EntityManager entityManager;

    @Override
    public void init() {
        try {
            entityManager.flush();
            entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE;").executeUpdate();
            truncateTable("user_room");
            truncateTable("room");
            truncateTable("users");
            entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE;").executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Table Initialization Error - " + e.getMessage());
        }
    }

    private void truncateTable(String tableName) {
        entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
    }
}
