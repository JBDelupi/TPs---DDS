package Models.Domain.Personas;

import Controller.Actores.TipoRol;
import Models.Domain.TipoDeDocumento;
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



    public Humano(){
        this.setRolNegocio(TipoRolNegocio.HUMANO);
    }



}