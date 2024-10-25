package Service.SistemaDeGeolocalizacion;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.Actores.Tecnico;
import Models.Domain.Personas.Actores.TipoRol;
import Service.APIPuntos.AreaCobertura;
import Service.APIPuntos.Punto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SistemaGeolocalizacion {
    private List<Heladera> heladerasDisponibles;
    private List<Persona> tecnicosRegistrados;
    private static SistemaGeolocalizacion instacia = null;

    private SistemaGeolocalizacion(){
    //    PseudoBaseDatosHeladera pseudoBaseDatosHeladera = new PseudoBaseDatosHeladera();
    //    heladerasDisponibles = pseudoBaseDatosHeladera.baseHeladeras;
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



    public Persona masCercanoAPunto(Punto punto) {
        List<Persona> listaFiltrada = this.tecnicosRegistrados.stream()
                .filter(f -> estaDentroDe(((Tecnico)f.getRol(TipoRol.TECNICO)).getArea(), punto))
                .toList();
        if (listaFiltrada.isEmpty()) {
            return null; // Probablemente haya que tirar una excepcion
        }
        Persona masCercano = listaFiltrada.get(0);
        double distanciaMinima = distanciaEntrePuntos(((Tecnico)masCercano.getRol(TipoRol.TECNICO)).getArea().getCentro(), punto);
        for (Persona tecnico : listaFiltrada) {
            double distancia = distanciaEntrePuntos(((Tecnico)tecnico.getRol(TipoRol.TECNICO)).getArea().getCentro(), punto);
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                masCercano = tecnico;
            }
        }

        return masCercano;
    }


}

