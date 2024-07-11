package Models.Domain.Builder.ContribucionBuilder;

import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.HacerseCargoDeHeladera;
import Models.Domain.Heladera.Heladera;

public class HacerseCargoDeHeladeraBuilder {
    HacerseCargoDeHeladera hacerseCargoDeHeladera;

    public HacerseCargoDeHeladeraBuilder(){
        this.hacerseCargoDeHeladera = new HacerseCargoDeHeladera();
    }

    public HacerseCargoDeHeladeraBuilder nombreCaracteristico(String nombre){
        this.hacerseCargoDeHeladera.setNombreCaracteristico(nombre);
        return this;
    }


    public HacerseCargoDeHeladeraBuilder heladera(Heladera heladera){
        this.hacerseCargoDeHeladera.setHeladera(heladera);
        return this;
    }




    public HacerseCargoDeHeladera construir(){
        return this.hacerseCargoDeHeladera;
    }
}
