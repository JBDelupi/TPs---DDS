package Service.Notificacion.Mensaje;

import Service.Notificacion.Correo.CorreoAdapter;
import Service.Notificacion.Notificacion;
import lombok.Getter;

@Getter
public class MensajeSuscripcion implements Mensaje {
    private String asunto;
    private String destinatario;
    private String contenido;

    public MensajeSuscripcion(String destinatario, String contenido) {
        this.asunto = "NUEVA PUBLICACION";
        this.destinatario = destinatario;
        this.generarMensaje(contenido);
    }

    @Override
    public void generarMensaje(String informacion) {
        this.contenido = " HOLA! DECCO COLABORACIONES INFORMA:" +
                "NUEVA PUBLICACION: " + informacion;

    }
}
