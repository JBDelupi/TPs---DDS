package Models.FormasDeContribucion;
import Models.Personas.Colaborador;
import Models.TipoFrecuencia;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DonacionDeDinero extends FormaDeContribucion {
    private LocalDate fechaDeDonacion;
    private Double monto;
    private TipoFrecuencia frecuencia;

    public DonacionDeDinero(Double monto, TipoFrecuencia frecuencia){
        this.monto = monto;
        this.frecuencia = frecuencia;
        this.fechaDeDonacion = LocalDate.now();
    }

    @Override
    public void generarDonacion(Colaborador humano){
        humano.agregarNuevaDonacion(this);
    }

}