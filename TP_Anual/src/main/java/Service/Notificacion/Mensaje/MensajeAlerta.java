package Service.Notificacion.Mensaje;

import lombok.Getter;

@Getter
public class MensajeAlerta implements Mensaje{
    private String asunto;
    private String destinatario;
    private String contenido;

    public MensajeAlerta(String destinatario, String contenido) {
        this.destinatario = destinatario;
        this.asunto = "NUEVO INCIDENTE";
        this.generarMensaje(contenido);
    }

    @Override
    public void generarMensaje(String informacion) {
        this.contenido = " HOLA! DECCO COLABORACIONES INFORMA:" +
                "NUEVO INCIDENTE EN LA HELADERA ID: " + informacion;

    }
}
