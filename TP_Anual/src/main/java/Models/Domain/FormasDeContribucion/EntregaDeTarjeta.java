package Models.Domain.FormasDeContribucion;

import Models.Domain.Tarjeta.Tarjeta;
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
        this.fechaDeDonacion = LocalDate.now();
    }


    @Override
    public Double generarPuntaje() {
        return 1 * 2.0;
    }
}
