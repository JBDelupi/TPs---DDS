package Models.Repository;

import Models.Domain.Excepciones.UsuarioYaTieneRolException;
import Models.Domain.Personas.Actores.Fisico;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.Actores.TipoRol;
import Models.Repository.EntityManager.EntityManagerHelper;
import Service.Server.exceptions.UserAlreadyExistsException;
import Service.Server.exceptions.UserNoLongerExistsException;
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
            Persona persona = (Persona) EntityManagerHelper.getEntityManager().createQuery(
                    "SELECT p FROM Persona p WHERE p.credencialDeAcceso.nombreUsuario = :correo"
            ).setParameter("correo", correo).getSingleResult();

            if(!persona.getAlta()){
                throw new UserNoLongerExistsException();
            }
            return persona;

        } catch (NoResultException e) {
            return null;
        }
    }

    public Persona buscarChequeandoRol(TipoRol tipoRol , int id) {
        Persona persona = this.buscar(Persona.class,id);
        if (persona.checkRol(tipoRol)){
            throw new UsuarioYaTieneRolException("Usuario ya tiene rol tecnico");
        }
        else return persona;
    }


}
