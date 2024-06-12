package Models.Domain.Builder.ContribucionBuilder;

import Models.Domain.FormasDeContribucion.DonacionDeDinero;
import Models.Domain.FormasDeContribucion.DonacionDeVianda;
import Models.Domain.Heladera;
import Models.Domain.Vianda;

public class DonacionDeViandaBuilder {
    private DonacionDeVianda donacionDeVianda;

    public DonacionDeViandaBuilder() {
        this.donacionDeVianda = new DonacionDeVianda();
    }

    public DonacionDeViandaBuilder vianda(Vianda vianda){
        this.donacionDeVianda.setVianda(vianda);
        return this;
    }

    public DonacionDeViandaBuilder heladera(Heladera heladera){
        this.donacionDeVianda.setHeladera(heladera);
        return this;
    }


    public DonacionDeVianda construir() {
        return this.donacionDeVianda;
    }
}
