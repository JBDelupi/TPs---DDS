package Service.APIPuntos;
import Service.APIPuntos.Punto;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class AreaCobertura {
    private Punto centro;
    private String radio;

    public AreaCobertura(){

    }
    public AreaCobertura(Punto centro, String radio){
        this.centro = centro;
        this.radio = radio;
    }
}

