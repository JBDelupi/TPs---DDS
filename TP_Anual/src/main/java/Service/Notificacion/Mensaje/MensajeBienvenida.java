package Service.Notificacion.Mensaje;

import Service.Notificacion.Correo.CorreoAdapter;
import Service.Notificacion.Notificacion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MensajeBienvenida implements Mensaje {
    private String asunto;
    private String destinatario;
    private String contenido;

    public MensajeBienvenida(String destinatario, String contenido) {
        this.destinatario = destinatario;
        this.asunto = "BIENVENIDO A DECCO COLABORACIONES";
        this.generarMensaje(contenido);
    }

    @Override
    public void generarMensaje(String informacion) {
        this.contenido = "<h1>¡Bienvenido a Decco Colaboraciones!</h1>"
                + "<p>Nos alegra que formes parte de nuestra comunidad. "
                + "Tu tarjeta de acceso a las heladeras es: <strong>" + informacion + "</strong></p>"
                + "<p>Consulta si tienes alguna duda o necesitas más información.</p>"
                + "<p><img src='https://i.ibb.co/hD9SKXr/De-Watermark-ai-1729225536715.png' alt='Logo Decco' width='200'></p>"
                + "<p>Saludos cordiales,<br>El equipo de Decco Colaboraciones</p>";

        // Aquí invocas al adaptador de correo para enviar la notificación
        Notificacion correoAdapter = new CorreoAdapter();
        correoAdapter.Notificar(this);
    }


}
