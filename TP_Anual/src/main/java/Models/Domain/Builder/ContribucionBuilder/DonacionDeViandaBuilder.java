package Models.Domain.Builder.ContribucionBuilder;

import Models.Domain.FormasDeContribucion.ContribucionesHumana.DonacionDeVianda;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Vianda;

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
