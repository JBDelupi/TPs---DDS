package Service.ImportadorCSV.DTO;

import Models.Domain.FormasDeContribucion.ContribucionesGenerales.DonacionDeDinero;
import Models.Domain.FormasDeContribucion.ContribucionesHumana.DistribucionDeViandas;
import Models.Domain.FormasDeContribucion.ContribucionesHumana.DonacionDeVianda;
import Models.Domain.FormasDeContribucion.ContribucionesHumana.EntregaDeTarjeta;
import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.HacerseCargoDeHeladera;
import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.OfrecerProducto;
import Models.Domain.FormasDeContribucion.Utilidades.Contribucion;
import Models.Domain.FormasDeContribucion.Utilidades.TipoDonacion;

public class FactoryContribucionDTO {

    public static Contribucion getContribucion(String tipoDonacion) {
        Contribucion contribucion = null;
        switch (tipoDonacion) {
            case "DINERO" -> {
                contribucion = new DonacionDeDinero();
                ((DonacionDeDinero) contribucion).setMonto(0.0);
            }
            case "ENTREGA_TARJETAS" -> {
                return new EntregaDeTarjeta();
            }
            case "REDISTRIBUCION_VIANDAS" -> {
                contribucion = new DistribucionDeViandas();
                ((DistribucionDeViandas) contribucion).setCantidadDeViandasAMover(0);
            }
            case "DONACION_VIANDAS" -> {
                return new DonacionDeVianda();
            }

            }

        return contribucion;
    }

}
