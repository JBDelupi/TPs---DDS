package Models.Domain.Builder;

import Models.Domain.Heladera;
import Models.Domain.Sensores.Sensor;
import Models.Domain.Sensores.SensorTemperatura;

public class HeladeraBuilder {
    private Heladera heladera;

    public HeladeraBuilder (){
        heladera = new Heladera();
    }

    public HeladeraBuilder temperaturaMax(Double temperaturaMax){
        heladera.setTemperaturaMax(temperaturaMax);
        return this;
    }

    public HeladeraBuilder temperaturaMin(Double temperaturaMin){
        heladera.setTemperaturaMin(temperaturaMin);
        return this;
    }

    public HeladeraBuilder capacidadMaxima(Integer capacidad){
        heladera.setCapacidadDeViandas(capacidad);
        return this;
    }


    public Heladera construir(){


        return heladera;
    }
}
