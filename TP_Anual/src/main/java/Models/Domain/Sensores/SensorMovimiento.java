package Models.Domain.Sensores;
import Models.Domain.Heladera;

public class SensorMovimiento implements Sensor {
    private Heladera heladera;

    public SensorMovimiento(Heladera heladera) {
        this.heladera = heladera;

    }

    public void chequear(){
        if(this.estaCerradaHeladera()){
            this.notificar();
        }
    }

    public void notificar(){
        System.out.println("Estan robando !!! D:");
    }


    public boolean estaCerradaHeladera(){
        return !this.heladera.getAbierto();
    }

}

