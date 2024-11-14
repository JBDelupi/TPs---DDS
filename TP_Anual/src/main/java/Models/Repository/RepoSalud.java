package Models.Repository;

import Models.Domain.Personas.Actores.Fisico;
import Models.Domain.Personas.Actores.TipoRol;
import Models.Domain.Tarjetas.Tarjeta;
import Models.Repository.EntityManager.EntityManagerHelper;

import javax.validation.constraints.Future;
import java.time.LocalDate;
import java.util.List;

public class RepoSalud extends Dao{
    public RepoSalud(Object type) {
        super(type);
    }

    public List<Tarjeta> getReporte() {

        LocalDate hoy = LocalDate.now();
        LocalDate sieteDiasAtras = hoy.minusDays(7);

        return EntityManagerHelper.getEntityManager()
                .createQuery(
                        "SELECT t FROM Tarjeta t " +
                                "JOIN t.usos b " +
                                "WHERE b.fecha BETWEEN :sieteDiasAtras AND :hoy", Tarjeta.class)
                .setParameter("sieteDiasAtras", sieteDiasAtras)
                .setParameter("hoy", hoy)
                .getResultList();
    }



}
