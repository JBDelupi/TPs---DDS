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

    @OneToOne(cascade = CascadeType.MERGE)
    private TarjetaAlimentar tarjeta;

    public EntregaDeTarjeta(TarjetaAlimentar tarjeta){
        this.tarjeta = tarjeta;
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

    public String getDetalle() {
        String unDetalle = "";
        unDetalle += "Tarjeta Codigo: " + (this.getTarjeta() != null && this.getTarjeta().getCodigo() != null ? this.getTarjeta().getCodigo() : "null");
        return unDetalle;
    }


}
