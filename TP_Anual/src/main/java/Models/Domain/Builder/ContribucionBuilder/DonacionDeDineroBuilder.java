package Models.Domain.Builder.ContribucionBuilder;

import Models.Domain.FormasDeContribucion.ContribucionesGenerales.DonacionDeDinero;
import Models.Domain.FormasDeContribucion.ContribucionesHumana.Utilidades.TipoFrecuencia;

public class DonacionDeDineroBuilder {
    private DonacionDeDinero donacionDeDinero;

    public DonacionDeDineroBuilder() {
        donacionDeDinero = new DonacionDeDinero();
    }

    public DonacionDeDineroBuilder monto(double monto) {
        this.donacionDeDinero.setMonto(monto);
        return this;
    }

    public DonacionDeDineroBuilder frecuencia(TipoFrecuencia frecuencia) {
        this.donacionDeDinero.setFrecuencia(frecuencia);
        return this;
    }



    public DonacionDeDinero construir(){
        return this.donacionDeDinero;
    }
}
