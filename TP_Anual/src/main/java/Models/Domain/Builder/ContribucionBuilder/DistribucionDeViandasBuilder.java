package Models.Domain.Builder.ContribucionBuilder;

import Models.Domain.FormasDeContribucion.ContribucionesHumana.DistribucionDeViandas;
import Models.Domain.Heladera.Heladera;

public class DistribucionDeViandasBuilder {
    private DistribucionDeViandas distribucionDeViandas;

    public DistribucionDeViandasBuilder() {
        this.distribucionDeViandas = new DistribucionDeViandas();
    }

    public DistribucionDeViandasBuilder heladeraOrigen(Heladera heladera){
        distribucionDeViandas.setHeladeraOrigen(heladera);
        return this;
    }

    public DistribucionDeViandasBuilder heladeraDestino(Heladera heladera){
        distribucionDeViandas.setHeladeraDestino(heladera);
        return this;
    }


    public DistribucionDeViandasBuilder cantidadDeViandasAMover(Integer cantidad){
        distribucionDeViandas.setCantidadDeViandasAMover(cantidad);
        return this;
    }

    public DistribucionDeViandasBuilder motivos(String motivo){
        distribucionDeViandas.setMotivo(motivo);
        return this;
    }

    public DistribucionDeViandas construir() {
        return this.distribucionDeViandas;
    }

}
