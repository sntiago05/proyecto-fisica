package fisica.persistence.dao;

import fisica.connection.JpaConnection;
import fisica.model.Ejercicio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

public class EjercicioDAO extends GenericDAO<Ejercicio, Long> {
    public EjercicioDAO() {
        super(Ejercicio.class);
    }

    public Ejercicio buscarPorTitulo(String titulo) {
        EntityManager em = JpaConnection.getEntityManager();
        try {
            return em.createQuery("SELECT e FROM Ejercicio e WHERE e.titulo = :titulo", Ejercicio.class)
                    .setParameter("titulo", titulo)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
