package Models.Domain.Personas;
import Service.APIPuntos.Punto;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class AreaCobertura {
    private Punto centro;
    private String radio;
}

