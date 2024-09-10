package Service.APIPuntos;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Punto {
    private String latitud;
    private String longitud;

    public Punto(String latitud, String longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }
    public Punto() {}
}
