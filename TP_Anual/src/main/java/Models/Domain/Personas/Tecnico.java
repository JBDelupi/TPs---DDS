package Models.Domain.Personas;

import Models.Domain.TipoDeDocumento;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Tecnico extends Persona {
    private String nombre;
    private String apellido;
    private TipoDeDocumento tipoDocumento;
    private String nroDocumento;
    private String cuil;
    private String medioDeContacto; // ver si son varios o uno solo
    private AreaCobertura area;

    public Tecnico() {
        this.setRolNegocio(TipoRolNegocio.TECNICO);
    }

}
