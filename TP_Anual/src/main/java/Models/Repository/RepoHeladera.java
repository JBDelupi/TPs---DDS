package Models.Repository;

import Models.Domain.Heladera.Incidentes.Alerta;
import Models.Repository.EntityManager.EntityManagerHelper;
import jakarta.persistence.NoResultException;

public class RepoHeladera extends Dao {

    public RepoHeladera(Object type) {
        super(type);
    }

    public Alerta ultimaAlerta(String id) {
        try {
            return  (Alerta) EntityManagerHelper.getEntityManager()
                    .createQuery("SELECT p FROM Alerta p WHERE p.id = :id AND p.solucionado = false")
                    .setParameter("id", id)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    public Object search(Class<?> objeto, String id) {
        return EntityManagerHelper.getEntityManager().find(objeto,id);

    }


}
