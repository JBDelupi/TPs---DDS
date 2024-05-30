package Models.FormasDeContribucion;

import Models.Personas.Colaborador;
import Models.Tarjeta.Tarjeta;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class EntregaDeTarjeta extends FormaDeContribucion{
    private Tarjeta tarjeta;
    private LocalDate fechaRegistro;
    public EntregaDeTarjeta(Tarjeta tarjeta){
        this.tarjeta = tarjeta;
        this.fechaRegistro = LocalDate.now();
    }


    @Override
    public Double generarPuntaje() {
        return 1 * 2.0;
    }
}
