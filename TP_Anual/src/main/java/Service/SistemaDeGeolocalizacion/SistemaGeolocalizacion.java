package Service.SistemaDeGeolocalizacion;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Tecnico;
import Service.APIPuntos.AreaCobertura;
import Service.APIPuntos.Punto;
import Service.APIPuntos.ServicioPuntosAPI;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class SistemaGeolocalizacion {
    private List<Heladera> heladerasDisponibles;
    private List<Tecnico> tecnicosRegistrados;
    private static SistemaGeolocalizacion instacia = null;

    private SistemaGeolocalizacion(){
        PseudoBaseDatosHeladera pseudoBaseDatosHeladera = new PseudoBaseDatosHeladera();
        heladerasDisponibles = pseudoBaseDatosHeladera.baseHeladeras;
    }

    public static SistemaGeolocalizacion getInstance(){
        if(instacia==null){
                instacia = new SistemaGeolocalizacion();
        }
            return instacia;
        }



    public List<Heladera> generarHeladerasDisponibles(AreaCobertura area, Integer cantidadDeAlimentos) {
        return heladerasDisponibles.stream().filter(
                f->f.tieneCantidadDisponible(cantidadDeAlimentos) && estaDentroDe(area, f.getDireccion().getCentro())
        ).toList();
    }


    public Double distanciaEntrePuntos (Punto punto1, Punto punto2){
        double dy = Double.parseDouble(punto1.getLatitud()) - Double.parseDouble(punto2.getLatitud());
        double dx =  Double.parseDouble(punto1.getLongitud()) - Double.parseDouble(punto2.getLongitud());

        return Math.sqrt(dx * dx + dy * dy);
    }

    public boolean estaDentroDe(AreaCobertura unArea, Punto punto) {
        double radio = Double.parseDouble(unArea.getRadio());
        Punto centro = unArea.getCentro();

        double distancia = distanciaEntrePuntos(punto, centro);
        return distancia <= radio;
    }



    public Tecnico masCercanoAPunto (Punto punto) {
        Tecnico masCercano = new Tecnico();
        List<Tecnico> listaFiltrada = new ArrayList<>();
        listaFiltrada = this.tecnicosRegistrados.stream().filter(f-> estaDentroDe(f.getArea(), punto)).toList();
        double distanciaMinima = distanciaEntrePuntos(listaFiltrada.get(0).getArea().getCentro(), punto);
        for (Tecnico tecnico : listaFiltrada) {
            double distancia = distanciaEntrePuntos(tecnico.getArea().getCentro(), punto);
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                masCercano = tecnico;
            }
        }
        return masCercano;
    }

}

