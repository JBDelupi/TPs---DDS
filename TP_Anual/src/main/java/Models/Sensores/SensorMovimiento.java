package Models.Sensores;

import Models.Heladera;

public class SensorMovimiento implements Sensor {
    private Heladera heladera;

    public SensorMovimiento(Heladera heladera) {
        this.heladera = heladera;

    }

    public void chequear(){
        if(!this.heladera.getAbierto()){
         System.out.println("Estan robando !!! D:");
        }
    }

    public void notificar(){

    }


}

// 1) Sacan de la heladera
// 2) Chequear si la heladera estaba abierta o cerrada
// 3) Si esta abierta - Caso donde no notifica el sensor
// 4) Si esta cerrada - Hay que notificar al sistema