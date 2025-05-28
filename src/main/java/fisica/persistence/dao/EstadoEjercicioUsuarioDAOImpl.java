package fisica.persistence.dao;

import fisica.connection.JpaConnection;
import fisica.model.Ejercicio;
import fisica.model.Estado;
import fisica.model.EstadoEjercicioUsuario;
import fisica.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class EstadoEjercicioUsuarioDAOImpl extends EstadoEjercicioUsuarioDAO {

    @Override
    public EstadoEjercicioUsuario buscarPorUsuarioYEjercicio(User usuario, Ejercicio ejercicio) {
        EntityManager em = JpaConnection.getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<EstadoEjercicioUsuario> cq = cb.createQuery(EstadoEjercicioUsuario.class);
            Root<EstadoEjercicioUsuario> root = cq.from(EstadoEjercicioUsuario.class);

            Predicate predUsuario = cb.equal(root.get("usuario"), usuario);
            Predicate predEjercicio = cb.equal(root.get("ejercicio"), ejercicio); // CAMBIADO

            cq.select(root).where(cb.and(predUsuario, predEjercicio));

            List<EstadoEjercicioUsuario> resultados = em.createQuery(cq).getResultList();
            return resultados.isEmpty() ? null : resultados.get(0);
        } finally {
            em.close();
        }
    }


    @Override
    public List<EstadoEjercicioUsuario> listarPorUsuario(User usuario) {
        EntityManager em = JpaConnection.getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<EstadoEjercicioUsuario> cq = cb.createQuery(EstadoEjercicioUsuario.class);
            Root<EstadoEjercicioUsuario> root = cq.from(EstadoEjercicioUsuario.class);

            cq.select(root).where(cb.equal(root.get("usuario"), usuario));
            return em.createQuery(cq).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<EstadoEjercicioUsuario> leaderboardPorEjercicio(Ejercicio ejercicio) {
        EntityManager em = JpaConnection.getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<EstadoEjercicioUsuario> cq = cb.createQuery(EstadoEjercicioUsuario.class);
            Root<EstadoEjercicioUsuario> root = cq.from(EstadoEjercicioUsuario.class);

            Predicate predEjercicio = cb.equal(root.get("ejercicio"), ejercicio);
            Predicate predEstado = cb.equal(root.get("estado"), Estado.Resuelto);

            cq.select(root)
                    .where(cb.and(predEjercicio, predEstado))
                    .orderBy(cb.asc(root.get("intentos")));

            return em.createQuery(cq).getResultList();
        } finally {
            em.close();
        }
    }


    @Override
    public void guardarOActualizar(EstadoEjercicioUsuario estado) {
        EntityManager em = JpaConnection.getEntityManager();
        em.getTransaction().begin();
        if (estado.getId() == null) {
            em.persist(estado);
        } else {
            em.merge(estado);
        }
        em.getTransaction().commit();
        em.close();
    }

}
