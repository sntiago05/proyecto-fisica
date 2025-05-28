package fisica.persistence.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import fisica.model.User;
import fisica.connection.JpaConnection;

public class UsuarioDAO extends GenericDAO<User, Long> {

    public UsuarioDAO() {
        super(User.class);
    }

    public User buscarPorNombre(String nombre) {
        EntityManager em = JpaConnection.getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.select(root).where(cb.equal(root.get("nickName"), nombre));
            return em.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}
