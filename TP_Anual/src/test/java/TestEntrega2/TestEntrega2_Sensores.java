package TestEntrega2;

import Models.Domain.Builder.HeladeraBuilder;
import Models.Domain.Heladera;
import Models.Domain.Sensores.Sensor;
import Models.Domain.Sensores.SensorMovimiento;
import Models.Domain.Sensores.SensorTemperatura;
import Models.Domain.Vianda;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestEntrega2_Sensores {
    Heladera heladera;
    @BeforeEach
    public void init() {

        HeladeraBuilder heladeraBuilder = new HeladeraBuilder();
        heladera = heladeraBuilder
                .temperaturaMin(2.00)
                .temperaturaMax(14.00)
                .capacidadMaxima(5)
                .construir();

    }

//--------------------------------- Sensor temperatura ---------------------------- //

    // supera temperatura maxima
    @Test
    public void temperaturaMaximaSuperada () {
        heladera.getSensorTemperatura().activar();
        heladera.setTemperaturaActual(15.1);
        Assertions.assertTrue(heladera.getTemperaturaActual() > heladera.getTemperaturaMax());
    }

    // supera temperatura minima
    @Test
    public void temperaturaMinimaSuperada () throws InterruptedException {
        heladera.getSensorTemperatura().activar();
        heladera.setTemperaturaActual(6.0);
        Thread.sleep(10000);
        heladera.setTemperaturaActual(1.0);


        Assertions.assertTrue(heladera.getTemperaturaActual() < heladera.getTemperaturaMin());
    }

    //--------------------------------- Sensor Movimiento ---------------------------- //
    @Test
    public void sensorMovimientoNoDetectaNadaSospechoso() throws InterruptedException {
        Vianda sanguche = new Vianda();
        Vianda milanesa = new Vianda();
        heladera.agregarVianda(sanguche,milanesa);
        heladera.setAbierto(true);

        Vianda unaVianda = heladera.obtenerVianda();

        Assertions.assertSame(unaVianda, sanguche);
    }

    @Test
    public void sensorMovimientoDetectaAlgoSospechoso() throws InterruptedException {
        Vianda sanguche = new Vianda();
        Vianda milanesa = new Vianda();
        heladera.agregarVianda(sanguche,milanesa);
        heladera.setAbierto(false);

        Vianda unaVianda = heladera.obtenerVianda();

        Assertions.assertSame(unaVianda, sanguche);
    }

}
