package Service.Broker;


import Service.Broker.Controllers.ActualizacionTemperaturaController;
import Service.Broker.Controllers.AlertaController;
import Service.Broker.Controllers.SolicitudAperturaController;

public class FactoryPublicacion {

    public static Object Publicacion(String nombre) {
        Object publicacion = null;
        switch (nombre) {
            case "temperatura": publicacion =  new ActualizacionTemperaturaController(); break;
            case "alerta": publicacion =  new AlertaController(); break;
            case "usoTarjeta": publicacion =  new SolicitudAperturaController(); break;
        }
        return publicacion;
    }


}
