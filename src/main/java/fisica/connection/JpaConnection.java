package fisica.connection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaConnection {
    private static final EntityManagerFactory entityManager = buildEntityManager();

    private static EntityManagerFactory buildEntityManager() {
        return Persistence.createEntityManagerFactory("fisica-demo");
    }

    public static EntityManager getEntityManager() {
        return entityManager.createEntityManager();
    }
}
