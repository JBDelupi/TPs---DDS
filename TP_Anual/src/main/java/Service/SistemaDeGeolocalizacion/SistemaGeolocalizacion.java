package Service.SistemaDeGeolocalizacion;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Tecnico;
import Service.APIPuntos.AreaCobertura;
import Service.APIPuntos.Punto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class SistemaGeolocalizacion {
    private AreaCobertura areaDeCobertura;
    private Integer cantidadDeAlimentos;
    private List<Heladera> heladerasDisponibles;
    private List<Tecnico> tecnicosRegistrados;

    public List<Heladera> generarHeladerasDisponibles() {
        return heladerasDisponibles.stream().filter(
                f->f.tieneCantidadDisponible(cantidadDeAlimentos) && estaDentroDe(areaDeCobertura, f.getDireccion().getCentro())
        ).toList();
    }

    // Deberiamos usar SINGLETON
    public SistemaGeolocalizacion(){
        this.tecnicosRegistrados = new ArrayList<>();
    }

    public boolean estaDentroDe(AreaCobertura unArea, Punto punto) {
        double radio = Double.parseDouble(unArea.getRadio());
        Punto centro = unArea.getCentro();


        double dy = Double.parseDouble(punto.getLatitud()) - Double.parseDouble(centro.getLatitud());
        double dx =  Double.parseDouble(punto.getLongitud()) - Double.parseDouble(centro.getLongitud());

        double distancia = Math.sqrt(dx * dx + dy * dy);
        //Para probar
        //System.out.println("Longitud: "+dx+ " Latitud "+dy);
        //System.out.println("Radio"+radio);
        return distancia <= radio;
    }

}

