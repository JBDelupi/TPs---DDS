package Models.Repository;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Fisico;
import Models.Repository.EntityManager.EntityManagerHelper;

import java.util.List;

public class RepoIncidente extends Dao{

    public RepoIncidente(Object type) {
        super(type);
    }

    public List<Heladera> queryHeladera() {
        return EntityManagerHelper.getEntityManager()
                .createQuery("FROM Heladera", Heladera.class)
                .getResultList();
    }

    public Object search(Class<?> objeto, String id) {
        return EntityManagerHelper.getEntityManager().find(objeto,id);

    }



}
