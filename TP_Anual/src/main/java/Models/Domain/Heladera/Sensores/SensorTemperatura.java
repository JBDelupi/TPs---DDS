package Models.Domain.Heladera.Sensores;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Incidentes.Alerta;
import Models.Domain.Heladera.Incidentes.Incidente;
import Models.Domain.Heladera.Incidentes.Utils.TipoAlerta;
import Models.Repository.PseudoBaseDatosAlerta;
import Models.Repository.PseudoBaseDatosHeladera;
import Service.TareaDiferida.AdapterChromeTask;

import Service.TareaDiferida.ChromeTask;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.util.random.RandomGenerator;


@Getter
@Setter
public class SensorTemperatura implements Sensor {
    @Transient
    private Heladera heladera;

    @Transient
    private AdapterChromeTask tareaProgramada;

    public SensorTemperatura(Heladera heladera) {
        this.heladera = heladera;
        this.tareaProgramada = new ChromeTask();
    }

    public void activar(){
        this.tareaProgramada.ejecutarTareaPrograma(5,this,"chequear");
    }

    public void desactivar(){
        this.tareaProgramada.pausarTarea();
    }

    public void chequear(){
       if ( this.superaTemperaturaMax() || this.superaTemperaturaMin() )
       {
           heladera.generarIncidente(TipoAlerta.TEMPERATURA);
           this.tareaProgramada.pausarTarea();
       }
    }


    public boolean superaTemperaturaMax(){
        return heladera.getTemperaturaActual() > heladera.getTemperaturaMax();
    }

    public boolean superaTemperaturaMin(){
        return heladera.getTemperaturaActual() < heladera.getTemperaturaMin();
    }


}
