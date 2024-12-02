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
        String[] info = informacion.split(",");
        this.contenido =
                "DECCO COLABORACIONES INFORMA: HAY UNA FALLA TECNICA DE UNA HELADERA DENTRO DE SU RADIO DE COBERTURA\n" +
                "\n" +
                "Falla técnica Reportada\n" +
                "Localidad: " + info[0] + "\n" +
                "Dirección: " + info[1] + "\n" +
                "Descripcion de la falla: " + info[2] + "\n" +
                "\n" +
                "Por favor, atienda esta falla lo antes posible.";


    }
}
