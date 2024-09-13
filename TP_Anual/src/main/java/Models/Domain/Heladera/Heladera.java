package Models.Domain.Heladera;

import Models.Domain.Excepciones.HeladeraLlenaException;
import Models.Domain.Excepciones.SinViandasException;
import Models.Domain.Heladera.Incidentes.Alerta;
import Models.Domain.Heladera.Incidentes.Incidente;
import Models.Domain.Heladera.Suscripciones.*;
import Models.Domain.Personas.DatosPersonales.Direccion;
import Models.Domain.Heladera.Sensores.Sensor;
import Models.Domain.Heladera.Sensores.SensorMovimiento;
import Models.Domain.Heladera.Sensores.SensorTemperatura;
import Service.APIPuntos.Punto;
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
        this.subscriptores = new ArrayList<>();
        this.sensorMovimiento = new SensorMovimiento(this);
        this.sensorTemperatura = new SensorTemperatura(this);
        this.setEstadoActual(EstadoHeladera.DISPONIBLE);
        this.cantidadDeFallas = 0;
        this.fechaDePuestaEnMarcha = LocalDate.now();
        this.cantidadDeviandasRetiradas = 0;
    }


    private Integer id;
    private Direccion direccion;
    private Punto coordenadas;
    private int capacidadDeViandas;
    private LocalDate fechaDePuestaEnMarcha;
    private EstadoHeladera estadoActual;
    private Double temperaturaMax; //Celsius
    private Double temperaturaMin;
    private Boolean abierto;
    private List<Vianda> viandas;
    private Boolean estaLlena = false;
    private Sensor sensorMovimiento;
    private Sensor sensorTemperatura;
    private Double temperaturaActual;
    private Integer cantidadDeFallas;
    private Integer cantidadDeviandasRetiradas;
    private List<ObserverHeladera> subscriptores;


    public void agregarVianda(Vianda ... vianda) {

        if ( capacidadDeViandas == viandas.size() ) {
            this.estaLlena = true;
            throw new HeladeraLlenaException("Esta llena la heladera");
        }

        Collections.addAll(this.viandas, vianda);
        generarNuevaPublicacion(TipoDePublicacion.FALTAN_N_VIANDAS);

    }



    public Vianda obtenerVianda() {
        if (this.viandas.isEmpty()) {
            throw new SinViandasException("No hay viandas");
        }
        Vianda vianda = viandas.get(0);
        viandas.remove(vianda);
        sensorMovimiento.chequear();
        generarNuevaPublicacion(TipoDePublicacion.N_VIANDAS_DISPONIBLES);
        return vianda;

    }

    public void registrarAlerta(){
        this.cantidadDeFallas++;
    }


    //  Registrar una falla
    public void registrarFalla() {
        this.cantidadDeFallas++;
    }



    // Reestablecer la cantidad de fallas a 0
    public void reestablecerFallas() {
        this.cantidadDeFallas = 0;
    }

    public void registrarVianda(){ this.cantidadDeviandasRetiradas++; }

    public void reestablecerViandas(){ this.cantidadDeviandasRetiradas = 0; }


    public void agregarSubscriptor(ObserverHeladera observer) {
        this.subscriptores.add(observer);
    }

    public void quitarSubscriptor(ObserverHeladera observer) {
        this.subscriptores.remove(observer);
    }

    public void generarNuevaPublicacion(TipoDePublicacion publicacion) {
        this.subscriptores.forEach(f->f.update(publicacion, this));
    }

    public Boolean tieneCantidadDisponible(Integer cantidad){
        return cantidad < (capacidadDeViandas - viandas.size());
    }

    public void notify(Alerta incidente){
        System.out.println("Hay un incidente, el incidente es :" + incidente.getTipo());
    }




}

