package Models.Domain.Builder;

import Models.Domain.Heladera.Vianda;

import java.time.LocalDate;

public class ViandaBuilder {
    Vianda vianda;

    public ViandaBuilder(){
        vianda = new Vianda();
    }


    public ViandaBuilder nombre(String nombre){
        this.vianda.setNombre(nombre);
        return this;
    }
    public ViandaBuilder fechaDeCaducidad(LocalDate fecha){
        this.vianda.setFechaDeCaducidad(fecha);
        return this;
    }
    public ViandaBuilder peso(int peso){
        this.vianda.setPeso(peso);
        return this;
    }

    public ViandaBuilder calorias(int calorias){
        this.vianda.setCalorias(calorias);
        return this;
    }


    public Vianda construir() {
        if(vianda.getPeso() == null){
            vianda.setPeso(0);
        }
        if(vianda.getCalorias() == null){
            vianda.setCalorias(0);
        }
        return vianda;
    }

}
