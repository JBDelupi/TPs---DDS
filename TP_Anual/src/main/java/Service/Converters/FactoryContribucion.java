package Service.Converters;

import Models.FormasDeContribucion.*;

public class FactoryContribucion {
    public static FormaDeContribucion controller(String nombre) {
        FormaDeContribucion controller = null;
        switch (nombre) {
            case "DINERO" : controller = new DonacionDeDinero(); break;
            case "DONACION_VIANDAS" : controller = new DonacionDeVianda(); break;
            case "REDISTRIBUCION_VIANDAS": controller = new DistribucionDeViandas(); break;
            case "ENTREGA_TARJETAS": controller = new EntregaDeTarjeta(); break;
        }
        return controller;
    }

}
