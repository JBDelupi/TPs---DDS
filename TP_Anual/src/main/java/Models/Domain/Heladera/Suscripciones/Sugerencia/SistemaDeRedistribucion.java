package Models.Domain.Heladera.Suscripciones.Sugerencia;

import Models.Domain.Heladera.EstadoHeladera;
import Models.Domain.Heladera.Heladera;
import Models.Repository.RepoHeladera;

import java.util.List;


public class SistemaDeRedistribucion {


    public SistemaDeRedistribucion(){
    }

    public static Sugerencia generarSugerencia(Integer cantidadViandas){
        int n = cantidadViandas;
        RepoHeladera  repoHeladera = new RepoHeladera();
        SugerenciaCompuesta sugerencia = new SugerenciaCompuesta();
        for(Heladera heladera :  repoHeladera.buscarTodos(Heladera.class) ){
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
