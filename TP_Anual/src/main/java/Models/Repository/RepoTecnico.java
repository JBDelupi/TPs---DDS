package Models.Repository;

import Models.Domain.Heladera.Heladera;
import Models.Repository.EntityManager.EntityManagerHelper;

import java.util.List;

public class RepoTecnico  extends Dao {

    public RepoTecnico(Object type) {
        super(type);
    }

    public List<Heladera> queryHeladera() {
        return EntityManagerHelper.getEntityManager()
                .createQuery("FROM Heladera", Heladera.class)
                .getResultList();
    }

}
