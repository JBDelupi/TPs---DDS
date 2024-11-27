package Models.Repository;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Rol;
import Models.Repository.EntityManager.EntityManagerHelper;

import java.util.List;

public class RepoRol extends Dao {


    public List<Rol> queryRol() {
        return EntityManagerHelper.getEntityManager()
                .createQuery("FROM Rol ", Rol.class)
                .getResultList();
    }

}
