package Models.Sensores;

import Models.Heladera;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@Getter
@Setter
public class SensorTemperatura implements Sensor {
    private Heladera heladera;

    public SensorTemperatura(Heladera heladera) {
        this.heladera = heladera;
        this.tareaProgramada();
    }

    public void tareaProgramada(){

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {
            this.chequear();
            System.out.println("Registrando temperatura: " + heladera.getTemperaturaActual());
        }, 0, 5, TimeUnit.MINUTES);
    }


    public void chequear(){

       if (heladera.getTemperaturaActual() > heladera.getTemperaturaMax() || heladera.getTemperaturaActual() < heladera.getTemperaturaMin())
       {
           this.notificar();
       }

    }

    public void notificar(){
        System.out.println(" TEMPERATURA EN PELIGRO DANGER D:");
    }


}

/*
public class SensorTemperatura implements Sensor {
    private Heladera heladera;
    private ScheduledExecutorService scheduler;

    public SensorTemperatura(Heladera heladera) {
        this.heladera = heladera;
        this.iniciarTareaProgramada();
    }

    public void iniciarTareaProgramada() {
        if (scheduler == null || scheduler.isShutdown()) {
            scheduler = Executors.newScheduledThreadPool(1);
            scheduler.scheduleAtFixedRate(() -> {
                this.chequear();
                System.out.println("Registrando temperatura: " + heladera.getTemperaturaActual());
            }, 0, 5, TimeUnit.SECONDS);
        }
    }

    public void pausarTareaProgramada() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
            try {
                if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                    scheduler.shutdownNow();
                }
            } catch (InterruptedException e) {
                scheduler.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    */