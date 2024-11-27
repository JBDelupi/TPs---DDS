package Models.Repository;

import Models.Domain.FormasDeContribucion.Utilidades.Contribucion;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.Actores.Rol;
import Models.Domain.Personas.Actores.TipoRol;
import Models.Repository.EntityManager.EntityManagerHelper;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class RepoContribucion extends Dao{


    public List<Contribucion> queryContribucion(String idUsuario) {
        Persona persona = EntityManagerHelper.getEntityManager().find(Persona.class, idUsuario);
        Colaborador colaborador = (Colaborador) persona.getRol(TipoRol.COLABORADOR);
        return colaborador.getContribuciones();
    }

    public List<Heladera> queryHeladeraDuenia() {
            return EntityManagerHelper.getEntityManager()
                    .createQuery("FROM Heladera as H where H.responsable IS NULL", Heladera.class)
                    .getResultList();
    }

    public List<Heladera> queryHeladeraDisponible() {
        return EntityManagerHelper.getEntityManager()
                .createQuery("FROM Heladera as H WHERE H.estaLlena = false ", Heladera.class)
                .getResultList();
    }






}
