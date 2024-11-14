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
        switch (tipoDonacion) {
            case "DINERO" -> {
                return new DonacionDeDinero();
            }
            case "ENTREGA_TARJETAS" -> {
                return new EntregaDeTarjeta();
            }
            case "REDISTRIBUCION_VIANDAS" -> {
                return new DistribucionDeViandas();
            }
            case "DONACION_VIANDAS" -> {
                return new DonacionDeVianda();
            }

            }

        return null;
    }

}
