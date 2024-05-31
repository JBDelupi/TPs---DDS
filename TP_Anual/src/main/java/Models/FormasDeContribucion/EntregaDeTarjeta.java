package Models.FormasDeContribucion;

import Models.Personas.Colaborador;
import Models.Tarjeta.Tarjeta;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class EntregaDeTarjeta extends FormaDeContribucion{
    private Tarjeta tarjeta;

    public EntregaDeTarjeta(Tarjeta tarjeta){
        this.tarjeta = tarjeta;
        this.fechaDeDonacion = LocalDate.now();
    }
    public EntregaDeTarjeta(){

    }


    @Override
    public Double generarPuntaje() {
        return 1 * 2.0;
    }
}
