package Models.Repository;

import Models.Domain.Personas.Actores.Persona;
import Models.Repository.EntityManager.EntityManagerHelper;
import Service.Server.exceptions.InvalidPasswordException;
import Service.Server.exceptions.UserNoLongerExistsException;
import Service.Validador.CredencialDeAcceso;
import jakarta.persistence.NoResultException;


public class RepoLogin extends Dao{


    public Persona credenciales(CredencialDeAcceso credencialDeAcceso) {
        try{
            Persona persona = (Persona) EntityManagerHelper.getEntityManager()
                .createQuery("SELECT p FROM Persona AS p WHERE p.credencialDeAcceso = :credencialDeAcceso")
                .setParameter("credencialDeAcceso", credencialDeAcceso)
                .getSingleResult();

            if(!persona.getAlta()){
                throw new UserNoLongerExistsException();
            }
            return persona;
        }
        catch(NoResultException e){
            throw new InvalidPasswordException();
        }
    }


}
