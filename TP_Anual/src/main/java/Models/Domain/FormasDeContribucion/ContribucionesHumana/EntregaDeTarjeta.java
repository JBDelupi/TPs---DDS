package Models.Domain.FormasDeContribucion.ContribucionesHumana;

import Models.Domain.FormasDeContribucion.Utilidades.FormaDeContribucion;
import Models.Domain.Tarjetas.TarjetaPersonaVulnerable;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class EntregaDeTarjeta extends FormaDeContribucion {
    private TarjetaPersonaVulnerable tarjetaPersonaVulnerable;

    public EntregaDeTarjeta(TarjetaPersonaVulnerable tarjetaPersonaVulnerable){
        this.tarjetaPersonaVulnerable = tarjetaPersonaVulnerable;
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
