package Models.Domain.Heladera.Suscripciones.Sugerencia;

import Models.Domain.Heladera.EstadoHeladera;
import Models.Domain.Heladera.Heladera;

import java.util.ArrayList;
import java.util.List;

public class SistemaDeRedistribucion {

    List<Heladera> heladerasDisponibles;

    public SistemaDeRedistribucion(List<Heladera> heladerasDisponibles){
        this.heladerasDisponibles = new ArrayList<>(heladerasDisponibles);
    }

    public Sugerencia generarSugerencia(Integer cantidadViandas){
        int n = cantidadViandas;
        SugerenciaCompuesta sugerencia = new SugerenciaCompuesta();
        for(Heladera heladera : heladerasDisponibles){
            if(heladera.getEstadoActual().equals(EstadoHeladera.DISPONIBLE)) {
                int capacidadDisponible = heladera.getCapacidadDeViandas() - heladera.getViandas().size();
                SugerenciaSimple sugerenciaSimple = new SugerenciaSimple();
                sugerenciaSimple.setCantidadDisponible(capacidadDisponible);
                sugerenciaSimple.setHeladera(heladera);
                n -= capacidadDisponible;
                sugerencia.agregarSugerencia(sugerenciaSimple);
                if (n <= 0) {
                    break;
                }
            }
        }

        return sugerencia;
    }
}
