package Models.Domain.FormasDeContribucion.ContribucionesGenerales;
import Models.Domain.FormasDeContribucion.Utilidades.Contribucion;
import Models.Domain.FormasDeContribucion.ContribucionesHumana.Utilidades.TipoFrecuencia;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DonacionDeDinero extends Contribucion {

    private Double monto;
    private TipoFrecuencia frecuencia;

    public Double generarPuntaje(){
        return monto * 0.5;
    }

    public DonacionDeDinero(){
        this.nombre = "Donacion de Dinero";
    }


}