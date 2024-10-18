package Service.Notificacion.Mensaje;

import lombok.Getter;

@Getter
public class MensajeTecnico implements Mensaje {
    private String asunto;
    private String destinatario;
    private String contenido;

    public MensajeTecnico(String destinatario, String contenido){
        this.asunto = "SOLICITUD DE UNA FALLA TECNICA";
        this.destinatario = destinatario;
        this.generarMensaje(contenido);
    }

    @Override
    public void generarMensaje(String informacion) {
        this.contenido = "DECCO COLABORACIONES INFORMA:" +
                "HAY UNA FALLA TECNICA DE UNA HELADERA DENTRO DE SU RADIO DE COBERTURA: " + informacion;

    }
}
