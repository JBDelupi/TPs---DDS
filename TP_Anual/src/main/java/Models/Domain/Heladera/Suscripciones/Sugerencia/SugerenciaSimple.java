package Models.Domain.Heladera.Suscripciones.Sugerencia;

import Models.Domain.Heladera.Heladera;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SugerenciaSimple implements Sugerencia{

    private Heladera heladera;
    private Integer cantidadDisponible;


    @Override
    public String mostrar() {
       return "Heladera: " + heladera.getId() + " Cantidad disponible: " + cantidadDisponible;
    }
}
