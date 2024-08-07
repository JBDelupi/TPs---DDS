package Models.Domain.FormasDeContribucion.ContribucionesHumana;

import Models.Domain.FormasDeContribucion.Utilidades.FormaDeContribucion;
import Models.Domain.Tarjetas.TarjetaAlimentar;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class EntregaDeTarjeta extends FormaDeContribucion {
    private TarjetaAlimentar tarjetaAlimentar;

    public EntregaDeTarjeta(TarjetaAlimentar tarjetaAlimentar){
        this.tarjetaAlimentar = tarjetaAlimentar;
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
