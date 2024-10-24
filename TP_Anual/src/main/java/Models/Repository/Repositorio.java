package Models.Repository;

import Models.Repository.EntityManager.EntityManagerHelper;
import Service.Validador.CredencialDeAcceso;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

import java.util.List;

public class Repositorio implements Dao{
    private Object type;

    public Repositorio(Object type){
        this.type = type;
    }

    public List buscarTodos() {
        CriteriaBuilder builder = EntityManagerHelper.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Object> critera = builder.createQuery((Class)this.type);
        critera.from((Class)this.type);
        List<Object> entities = EntityManagerHelper.getEntityManager().createQuery(critera).getResultList();

        return entities;
    }
    public Object buscar(int id) {
        return EntityManagerHelper.getEntityManager().find((Class)type, id);
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

    public void cerrarTransaccion() {
        EntityManagerHelper.getEntityManager().close();
    }


    public Object credenciales(CredencialDeAcceso credencialDeAcceso) {
        return EntityManagerHelper.getEntityManager()
                .createQuery("SELECT p FROM Persona AS p WHERE p.credencialDeAcceso = :credencialDeAcceso")
                .setParameter("credencialDeAcceso", credencialDeAcceso)
                .getSingleResult();
    }

}
