package Models.Domain.FormasDeContribucion.Utilidades;

import Models.Domain.Personas.Actores.Colaborador;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public abstract class Contribucion {

    protected String nombre;

    protected LocalDate fechaDeDonacion = LocalDate.now();

    public void generarContribucion(Colaborador persona){ persona.agregarNuevaDonacion(this);}

    public Double generarPuntaje(){
        return 0.0;
    }
}
