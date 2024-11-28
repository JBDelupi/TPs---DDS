package Models.Repository;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Fisico;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.Actores.TipoRol;
import Models.Repository.EntityManager.EntityManagerHelper;
import Service.Server.exceptions.UserAlreadyExistsException;
import jakarta.persistence.NoResultException;

import java.util.List;

public class RepoPersona extends Dao {


    public List<Persona> personasRol(TipoRol rol) {
        return EntityManagerHelper.getEntityManager()
                .createQuery("SELECT p FROM Persona p JOIN p.roles r WHERE r.tipo = :rol", Persona.class)
                .setParameter("rol", rol)
                .getResultList();
    }

    public List<Fisico> fisicoRol(TipoRol rol) {
        return EntityManagerHelper.getEntityManager()
                .createQuery("SELECT p FROM Fisico p JOIN p.roles r WHERE r.tipo = :rol", Fisico.class)
                .setParameter("rol", rol)
                .getResultList();
    }


    public void existeUsuario(String nombreUsuario) {
        try {
            Persona persona = (Persona) EntityManagerHelper.getEntityManager().createQuery(
                    "SELECT p FROM Persona p WHERE p.credencialDeAcceso.nombreUsuario = :nombreUsuario"
            ).setParameter("nombreUsuario", nombreUsuario).getSingleResult();

            throw new UserAlreadyExistsException();
        } catch (NoResultException e) {
        // QUE CONTINUE EL FLUJO
        }
    }

    public Persona existeUsuarioSso(String correo) {
        try {
            return (Persona) EntityManagerHelper.getEntityManager().createQuery(
                    "SELECT p FROM Persona p WHERE p.credencialDeAcceso.nombreUsuario = :correo"
            ).setParameter("correo", correo).getSingleResult();

        } catch (NoResultException e) {
            return null;
        }
    }


}
