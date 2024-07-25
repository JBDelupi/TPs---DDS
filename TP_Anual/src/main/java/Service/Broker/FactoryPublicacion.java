package Service.Broker;


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
