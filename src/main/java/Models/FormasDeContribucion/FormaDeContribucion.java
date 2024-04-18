package Models.FormasDeContribucion;

import Models.Colaborador;
import Models.Humano;
import lombok.Getter;

@Getter

public abstract class FormaDeContribucion {


    public abstract void generarDonacion(Colaborador humano);
}
