package Models.Domain;

import Models.Domain.Sensores.Sensor;
import Models.Domain.Sensores.SensorMovimiento;
import Models.Domain.Sensores.SensorTemperatura;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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

    public void agregarVianda(Vianda ... vianda) {
        if ( capacidadDeViandas > viandas.size() ) {
            Collections.addAll(this.viandas, vianda);
        } else {
            estaLlena = true;
        }
    }
    public Vianda obtenerVianda() {
        if (!this.viandas.isEmpty()) {
            Vianda vianda = viandas.get(0);
            viandas.remove(vianda);
            sensorMovimiento.activar();
            return vianda;
        }
        return null;
    }


}

