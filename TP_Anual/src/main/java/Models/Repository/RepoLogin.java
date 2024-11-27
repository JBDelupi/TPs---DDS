package Models.Repository;

import Models.Repository.EntityManager.EntityManagerHelper;
import Service.Server.exceptions.InvalidPasswordException;
import Service.Validador.CredencialDeAcceso;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

import java.util.List;

public class RepoLogin extends Dao{


    public Object credenciales(CredencialDeAcceso credencialDeAcceso) {
        try{
            return  EntityManagerHelper.getEntityManager()
                .createQuery("SELECT p FROM Persona AS p WHERE p.credencialDeAcceso = :credencialDeAcceso")
                .setParameter("credencialDeAcceso", credencialDeAcceso)
                .getSingleResult();
        }
        catch(NoResultException e){
            throw new InvalidPasswordException();
        }
    }


}
