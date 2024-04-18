package Models.FormasDeContribucion;

import Models.Colaborador;
import Models.Heladera;
import Models.Humano;
import Models.Vianda;
import lombok.Setter;

import java.time.LocalDate;

@Setter
public class DonacionDeVianda extends FormaDeContribucion{
    private Vianda vianda;
    private LocalDate fechaDonacion;
    private Heladera heladera;

    public DonacionDeVianda(Vianda vianda, Heladera heladera){
        this.vianda = vianda;
        this.heladera = heladera;
    }


    @Override
    public void generarDonacion(Colaborador humano) {
        humano.agregarNuevaDonacion(this);

    }

}


