package Models.Domain.Personas.Actores;

import Models.Domain.Personas.DatosPersonales.TipoDeDocumento;
import Models.Domain.Personas.Utilidades.TipoRolNegocio;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class Humano extends Colaborador {
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private TipoDeDocumento tipoDeDocumento;
    private String numeroDocumento;
    private Integer cantidadViandasDonadas;


    public Humano(){
        this.setRolNegocio(TipoRolNegocio.HUMANO);
    }

    public void reestablecerViandas(){this.cantidadViandasDonadas = 0;}
    public void agregarVianda(){this.cantidadViandasDonadas++;}

}