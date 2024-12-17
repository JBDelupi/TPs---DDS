package Models.Repository;

import Models.Repository.EntityManager.EntityManagerHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

import java.util.List;

public abstract class Dao {

    public <T> List<T> buscarTodos(Class<T> type) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        return EntityManagerHelper.getEntityManager().createQuery(criteria).getResultList();
    }

    public <T> T buscar(Class<T> type, int id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        return em.find(type, id);
    }


    public void agregar(Object unObjeto) {
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().persist(unObjeto);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
        EntityManagerHelper.getEntityManager().close();
    }


    public void modificar(Object unObjeto) {
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().merge(unObjeto);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
        EntityManagerHelper.getEntityManager().close();
    }

    public void eliminar(Object unObjeto) {
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        Object reattached = EntityManagerHelper.getEntityManager().merge(unObjeto);
        EntityManagerHelper.getEntityManager().remove(reattached);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
        EntityManagerHelper.getEntityManager().close();
    }

}
