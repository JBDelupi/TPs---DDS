package Models.Repository;

import Models.Domain.Excepciones.UsuarioYaTieneRolException;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.Actores.TipoRol;
import Models.Repository.EntityManager.EntityManagerHelper;

import java.util.List;

public class RepoTecnico  extends Dao {

    public List<Heladera> queryHeladera() {
        return EntityManagerHelper.getEntityManager()
                .createQuery("FROM Heladera", Heladera.class)
                .getResultList();
    }

    public Persona buscarChequeandoRol(Class<Persona> usuario, int id) {
        Persona persona = this.buscar(usuario, id);
        if (persona.checkRol(TipoRol.TECNICO)){
            throw new UsuarioYaTieneRolException("Usuario ya tiene rol tecnico");
        }
        else return persona;
    }

}
