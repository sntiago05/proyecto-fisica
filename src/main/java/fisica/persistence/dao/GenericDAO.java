package fisica.persistence.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import fisica.connection.JpaConnection;

import java.util.List;

public abstract class GenericDAO<T, ID> {

    private Class<T> entityClass;

    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void guardar(T entidad) {
        EntityManager em = JpaConnection.getEntityManager();
        em.getTransaction().begin();
        em.persist(entidad);
        em.getTransaction().commit();
        em.close();
    }

    public T buscarPorId(ID id) {
        EntityManager em = JpaConnection.getEntityManager();
        T entidad = em.find(entityClass, id);
        em.close();
        return entidad;
    }

    public void actualizar(T entidad) {
        EntityManager em = JpaConnection.getEntityManager();
        em.getTransaction().begin();
        em.merge(entidad);
        em.getTransaction().commit();
        em.close();
    }

    public void eliminar(T entidad) {
        EntityManager em = JpaConnection.getEntityManager();
        em.getTransaction().begin();
        T entidadManaged = em.merge(entidad);
        em.remove(entidadManaged);
        em.getTransaction().commit();
        em.close();
    }

    public List<T> listarTodos() {
        EntityManager em = JpaConnection.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        cq.select(root);
        List<T> resultados = em.createQuery(cq).getResultList();
        em.close();
        return resultados;
    }
}
