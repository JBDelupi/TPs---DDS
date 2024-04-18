package Models;

import java.time.LocalDate;

public class PersonaVulnerable {
    private String nombre;
    private LocalDate fechaDeNacimiento;
    private LocalDate fechaRegistro;
    private Boolean flagSituacionDeCalle;
    private Direccion domicilio;
    private TipoDeDocumento tipoDeDocumento;
    private Integer numeroDocumento;
    private Integer menoresACargo;

    public Boolean tieneMenoresACargo(){
        return menoresACargo>0;
    }
}
