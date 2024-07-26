package Models.Domain.Personas.Actores;

import Models.Domain.Personas.DatosPersonales.TipoDeDocumento;
import Service.APIPuntos.AreaCobertura;
import Models.Domain.Personas.Utilidades.TipoRolNegocio;
import Service.Notificacion.Notificacion;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Tecnico extends Persona {
    private String nombre;
    private String apellido;
    private TipoDeDocumento tipoDocumento;
    private String nroDocumento;
    private String cuil;
    private AreaCobertura area;
    private Notificacion medioDeNotificacion;
    private String correoElectronico;

    public Tecnico() {
        this.setRolNegocio(TipoRolNegocio.TECNICO);
    }

}
