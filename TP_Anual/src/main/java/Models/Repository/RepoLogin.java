package Models.Repository;

import Models.Repository.EntityManager.EntityManagerHelper;
import Service.Validador.CredencialDeAcceso;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

import java.util.List;

public class RepoLogin extends Dao{
    private Object type;

    public RepoLogin(Object type){
        super(type);
    }

    public Object credenciales(CredencialDeAcceso credencialDeAcceso) {
        return EntityManagerHelper.getEntityManager()
                .createQuery("SELECT p FROM Persona AS p WHERE p.credencialDeAcceso = :credencialDeAcceso")
                .setParameter("credencialDeAcceso", credencialDeAcceso)
                .getSingleResult();
    }


}
