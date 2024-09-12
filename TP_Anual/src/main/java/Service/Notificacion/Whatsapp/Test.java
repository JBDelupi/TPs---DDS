package Service.Notificacion.Whatsapp;

import Models.Domain.Builder.UsuariosBuilder.HumanoBuilder;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Incidentes.FallaTecnica;
import Models.Domain.Heladera.Suscripciones.*;
import Models.Domain.Heladera.Vianda;
import Models.Domain.Personas.Actores.Humano;
import Service.Notificacion.Mensaje;
import Service.Notificacion.Whatsapp.WhatsappAdapter;



public class Test {
    public static void main(String[] args) {

        Humano pepito = new Humano();

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
