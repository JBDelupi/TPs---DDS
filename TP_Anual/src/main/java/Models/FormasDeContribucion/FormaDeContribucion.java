package Models.FormasDeContribucion;

import Models.Personas.Colaborador;
import lombok.Getter;

@Getter

public abstract class FormaDeContribucion {


    public abstract void generarDonacion(Colaborador humano);
}
