package Models.Domain.FormasDeContribucion;

import Models.Domain.Personas.Colaborador;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public abstract class FormaDeContribucion {

    protected LocalDate fechaDeDonacion;

    public void generarContribucion(Colaborador persona){ persona.agregarNuevaDonacion(this);}


    public Double generarPuntaje(){
        return 0.0;
    }
}
