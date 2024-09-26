package Models.Domain.Personas.Actores;

import Models.Domain.Personas.DatosPersonales.TipoDeDocumento;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class Fisico extends Persona {
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private TipoDeDocumento tipoDeDocumento;
    private String numeroDocumento;


    public Fisico(){

    }

// ponerlo  en colaborador
//    public void reestablecerViandas(){this.cantidadViandasDonadas = 0;}
//    public void agregarVianda(){this.cantidadViandasDonadas++;}
//

}