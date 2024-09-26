package Service.Notificacion.Whatsapp;

import Models.Domain.Personas.Actores.Fisico;
import Service.Notificacion.Mensaje;


public class Test {
    public static void main(String[] args) {

        Fisico pepito = new Fisico();

        pepito.setCodigoDeNotificacion("5491121619445");
        pepito.setNombre("Pepito");
        pepito.setApellido("amskdmasmdla");
        pepito.setMedioDeNotificacion(new WhatsappAdapter());

        Mensaje mensaje = new Mensaje();
        mensaje.setContenido("Hola "+pepito.getNombre()+" NOTIFICACION DE SUSCRIPCION");
        mensaje.setDestinatario(pepito.getCodigoDeNotificacion());

        pepito.getMedioDeNotificacion().Notificar(mensaje);
    }


}
