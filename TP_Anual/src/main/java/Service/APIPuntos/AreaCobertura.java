package Service.APIPuntos;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@Embeddable
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

