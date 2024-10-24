package Service.APIPuntos;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Embeddable
@NoArgsConstructor

public class Punto {
    @Column(name = "latitud")
    private String latitud;

    @Column(name = "longitud")
    private String longitud;

    public Punto(String latitud, String longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }
}
