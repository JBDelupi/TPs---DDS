package Models.Repository;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Fisico;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.Actores.TipoRol;
import Models.Repository.EntityManager.EntityManagerHelper;

import java.util.List;

public class RepoPersona extends Dao {
    public RepoPersona(Object type) {
        super(type);
    }

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


}
