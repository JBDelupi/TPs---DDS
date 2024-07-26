package Models.Domain.FormasDeContribucion.ContribucionesGenerales;
import Models.Domain.FormasDeContribucion.Utilidades.FormaDeContribucion;
import Models.Domain.FormasDeContribucion.ContribucionesHumana.Utilidades.TipoFrecuencia;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DonacionDeDinero extends FormaDeContribucion {

    private Double monto;
    private TipoFrecuencia frecuencia;

    public Double generarPuntaje(){
        return monto * 0.5;
    }

}