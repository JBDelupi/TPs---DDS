package Models.Domain.Builder;

import Models.Domain.Excepciones.CapacidadHeladeraException;
import Models.Domain.Personas.DatosPersonales.Direccion;
import Models.Domain.Heladera.Heladera;

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

    public HeladeraBuilder Direccion(Direccion direccion){
        heladera.setDireccion(direccion);
        return this;
    }

    public HeladeraBuilder abierto(Boolean abierto){
        heladera.setAbierto(abierto);
        return this;
    }
    


    public Heladera construir(){
        if(this.heladera.getCapacidadDeViandas() == 0){
            throw new CapacidadHeladeraException("La capacidad de viandas no puede ser cero ");
        }
        else{
            this.heladera.setCapacidadActual(this.heladera.getCapacidadDeViandas());
        }
        return heladera;
    }
}
