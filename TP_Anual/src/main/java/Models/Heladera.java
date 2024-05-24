package Models;

import Models.Sensores.Sensor;
import Models.Sensores.SensorMovimiento;
import Models.Sensores.SensorTemperatura;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Heladera {

    public Heladera() {
        this.viandas = new ArrayList<>();
        this.sensorMovimiento = new SensorMovimiento(this);
        this.sensorTemperatura = new SensorTemperatura(this);

    }

    private Direccion direccion;
    private Double longitud;
    private int capacidadDeViandas;
    private LocalDate fechaDePuestaEnMarcha;
    private EstadoHeladera actual;
    private Double temperaturaMax; //Celsius
    private Double temperaturaMin;
    private Boolean abierto;
    private List<Vianda> viandas;
    private Boolean estaLlena = false;
    private Sensor sensorMovimiento;
    private Sensor sensorTemperatura;
    private Double temperaturaActual;

    public void agregarVianda(Vianda vianda) {
        if ( capacidadDeViandas > viandas.size() ) {
            viandas.add(vianda);
        } else {
            estaLlena = true;
        }
    }
    public Vianda obtenerVianda() {
        Vianda vianda = viandas.get(0);
        viandas.remove(vianda);
        sensorMovimiento.chequear();
        return vianda;
    }

}

