package Models.Repository;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Incidentes.Alerta;
import Models.Domain.Heladera.Incidentes.FallaTecnica;
import Models.Repository.EntityManager.EntityManagerHelper;
import jakarta.persistence.NoResultException;

import java.util.List;

public class RepoHeladera extends Dao {


    public Alerta ultimaAlerta(String id) {
        try {
            return  (Alerta) EntityManagerHelper.getEntityManager()
                    .createQuery("SELECT p FROM Alerta p WHERE p.id = :id AND p.solucionado = false")
                    .setParameter("id", id)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }


    public List<Heladera> buscarMisHeladeras(Integer idUsuario) {
        return EntityManagerHelper.getEntityManager()
                .createQuery("SELECT h FROM Heladera h WHERE h.responsable.id = :idUsuario", Heladera.class)
                .setParameter("idUsuario", idUsuario)
                .getResultList();
    }

    public List<FallaTecnica> buscarFallasPorHeladera(int idHeladera) {
        return EntityManagerHelper.getEntityManager()
                .createQuery("SELECT f FROM FallaTecnica f WHERE f.heladera.id = :idHeladera", FallaTecnica.class)
                .setParameter("idHeladera", idHeladera)
                .getResultList();
    }


}
