package Models.Domain.FormasDeContribucion.ContribucionesGenerales;
import Models.Domain.FormasDeContribucion.Utilidades.Contribucion;
import Models.Domain.FormasDeContribucion.ContribucionesHumana.Utilidades.TipoFrecuencia;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

@Entity
@DiscriminatorValue("donacion_de_dinero")
public class DonacionDeDinero extends Contribucion {

    @Column(name = "monto")
    private Double monto;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_de_frecuencia")
    private TipoFrecuencia frecuencia;

    public Double generarPuntaje(){
        return monto * 0.5;
    }

    public DonacionDeDinero(){
        this.nombre = "Donacion de Dinero";
    }


}