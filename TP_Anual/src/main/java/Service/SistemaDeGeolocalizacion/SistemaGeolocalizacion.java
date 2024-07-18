package Service.SistemaDeGeolocalizacion;

import Models.Domain.Heladera.Heladera;
import Service.APIPuntos.AreaCobertura;
import Service.APIPuntos.Punto;

import java.util.List;

public class SistemaGeolocalizacion {
    private AreaCobertura areaDeCobertura;
    private Integer cantidadDeAlimentos;
    private List<Heladera> heladerasDisponibles;

    public List<Heladera> generarHeladerasDisponibles() {
        return heladerasDisponibles.stream().filter(
                f->f.tieneCantidadDisponible(cantidadDeAlimentos) && estaDentroDe(areaDeCobertura, f.getDireccion().getCentro())
        ).toList();
    }

    public boolean estaDentroDe(AreaCobertura unArea, Punto punto) {
        double radio = Double.parseDouble(unArea.getRadio());
        Punto centro = unArea.getCentro();


        double dx = Double.parseDouble(punto.getLatitud()) - Double.parseDouble(centro.getLatitud());
        double dy =  Double.parseDouble(punto.getLongitud()) - Double.parseDouble(centro.getLongitud());

        double distancia = Math.sqrt(dx * dx + dy * dy);

        return distancia <= radio;
    }

}

