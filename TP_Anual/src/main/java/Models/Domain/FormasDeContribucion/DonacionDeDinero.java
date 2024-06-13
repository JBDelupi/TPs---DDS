package Models.Domain.FormasDeContribucion;
import Models.Domain.TipoFrecuencia;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class DonacionDeDinero extends FormaDeContribucion {

    private Double monto;
    private TipoFrecuencia frecuencia;

    public DonacionDeDinero(){

    }

    public Double generarPuntaje(){
        return monto * 0.5;
    }

}