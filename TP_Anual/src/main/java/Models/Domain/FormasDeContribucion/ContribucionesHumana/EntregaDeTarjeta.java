package Models.Domain.FormasDeContribucion.ContribucionesHumana;

import Models.Domain.FormasDeContribucion.Utilidades.Contribucion;
import Models.Domain.Tarjetas.TarjetaAlimentar;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter

@Entity
@DiscriminatorValue("entrega_de_tarjeta")
public class EntregaDeTarjeta extends Contribucion {

    @OneToOne(cascade = CascadeType.PERSIST)
    private TarjetaAlimentar tarjetaAlimentar;

    public EntregaDeTarjeta(TarjetaAlimentar tarjetaAlimentar){
        this.tarjetaAlimentar = tarjetaAlimentar;
        this.fechaDeDonacion = LocalDate.now();
        this.nombre = "Entrega de Tarjeta";
    }

    public EntregaDeTarjeta(){
        this.fechaDeDonacion = LocalDate.now();
        this.nombre = "Entrega de Tarjeta";
    }


    @Override
    public Double generarPuntaje() {
        return 1 * 2.0;
    }

}
