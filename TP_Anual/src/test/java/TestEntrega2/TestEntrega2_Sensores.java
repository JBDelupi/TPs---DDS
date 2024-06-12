package TestEntrega2;

import Models.Domain.Heladera;
import Models.Domain.Sensores.SensorTemperatura;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestEntrega2_Sensores {
    Heladera heladera;

    @BeforeEach
    public void init() {
        heladera = new Heladera();
    }

//--------------------------------- Sensor temperatura ----------------------------

    // supera temperatura maxima
    @Test
    public void temperaturaMaximaSuperada () {
        heladera.setTemperaturaMin(2.0);
        heladera.setTemperaturaMax(15.0);
        heladera.setTemperaturaActual(15.1);
        heladera.getSensorTemperatura().chequear();
    }

    // supera temperatura minima
    @Test
    public void temperaturaMinimaSuperada () throws InterruptedException {
        heladera.setTemperaturaMin(2.0);
        heladera.setTemperaturaMax(15.0);
        heladera.setTemperaturaActual(6.0);
        System.out.println("Chequeando..." + heladera.getTemperaturaActual());
        heladera.setSensorTemperatura(new SensorTemperatura(heladera));
        Thread.sleep(10000);
        System.out.println("Chequeando..." + heladera.getTemperaturaActual());
        heladera.setTemperaturaActual(1.0);
        System.out.println("Chequeando..." + heladera.getTemperaturaActual());
        Assertions.assertTrue(heladera.getTemperaturaActual() < heladera.getTemperaturaMin());
    }


}
