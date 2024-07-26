package Service.Broker;


import Service.Broker.Controllers.ActualizacionTemperatura;
import Service.Broker.Controllers.Alerta;
import Service.Broker.Controllers.SolicitudApertura;

public class FactoryPublicacion {

    public static Object Publicacion(String nombre) {
        Object publicacion = null;
        switch (nombre) {
            case "temperatura": publicacion =  new ActualizacionTemperatura(); break;
            case "alerta": publicacion =  new Alerta(); break;
            case "usoTarjeta": publicacion =  new SolicitudApertura(); break;
        }
        return publicacion;
    }


}
