package Models.Personas;

import Models.Direccion;
import Models.TipoDeDocumento;

import java.time.LocalDate;

public class PersonaVulnerable {
    private String nombre;
    private LocalDate fechaDeNacimiento;
    private LocalDate fechaRegistro;
    private Boolean flagSituacionDeCalle;
    private Direccion domicilio;
    private TipoDeDocumento tipoDeDocumento;
    private String numeroDocumento;
    private Integer menoresACargo;

    public Boolean tieneMenoresACargo(){
        return menoresACargo>0;
    }
}
