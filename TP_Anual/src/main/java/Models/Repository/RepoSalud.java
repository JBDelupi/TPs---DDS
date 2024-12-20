package Models.Repository;


import Models.Domain.Tarjetas.Tarjeta;
import Models.Repository.EntityManager.EntityManagerHelper;

import java.time.LocalDate;
import java.util.List;

public class RepoSalud extends Dao{

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
