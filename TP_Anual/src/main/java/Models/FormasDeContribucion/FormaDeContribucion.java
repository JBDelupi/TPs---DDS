package Models.FormasDeContribucion;

import Models.Personas.Colaborador;
import lombok.Getter;

@Getter

public abstract class FormaDeContribucion {
    public void generarContribucion(Colaborador persona){ persona.agregarNuevaDonacion(this);}


    public Double generarPuntaje(){
        return 0.0;
    }
}
