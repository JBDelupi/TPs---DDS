package Service.Notificacion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mensaje {
    private String remitente;
    private String asunto;
    private String destinatario;
    private String contenido;
}
