package Models.Domain.Heladera;

import Models.Domain.Excepciones.HeladeraLlenaException;
import Models.Domain.Excepciones.SinViandasException;
import Models.Domain.Heladera.Incidentes.Alerta;
import Models.Domain.Heladera.Incidentes.Utils.TipoAlerta;
import Models.Domain.Heladera.Suscripciones.*;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.DatosPersonales.Direccion;
import Models.Domain.Heladera.Sensores.Sensor;
import Models.Domain.Heladera.Sensores.SensorMovimiento;
import Models.Domain.Heladera.Sensores.SensorTemperatura;
import Models.Repository.PseudoBaseDatosAlerta;
import Service.APIPuntos.Punto;
import Service.Notificacion.Mensaje.MensajeAlerta;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.random.RandomGenerator;

@Getter
@Setter
public class Heladera {

    public Heladera() {
        this.viandas = new ArrayList<>();
        this.suscriptores = new ArrayList<>();
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
    private List<ObserverHeladera> suscriptores;
    private Persona responsable;

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
    public void registrarFalla() {
        this.cantidadDeFallas++;
    }



    public void reestablecerFallas() {
        this.cantidadDeFallas = 0;
    }
    public void registrarVianda(){ this.cantidadDeviandasRetiradas++; }
    public void reestablecerViandas(){ this.cantidadDeviandasRetiradas = 0; }



    public void agregarSubscriptor(ObserverHeladera observer) {
        this.suscriptores.add(observer);
    }

    public void quitarSubscriptor(ObserverHeladera observer) {
        this.suscriptores.remove(observer);
    }

    public void generarNuevaPublicacion(TipoDePublicacion publicacion) {
        this.suscriptores.forEach(f->f.update(publicacion, this));
    }

    public Boolean tieneCantidadDisponible(Integer cantidad){
        return cantidad < (capacidadDeViandas - viandas.size());
    }


    public void notificar(Alerta incidente){
        if(responsable != null){
            responsable.notify(new MensajeAlerta(responsable.getCodigoDeNotificacion(), this.getId() +" Tipo alerta: " + incidente.getTipo() ));
        }
    }

    public void generarIncidente (TipoAlerta tipo){
        Alerta nuevaAlerta = new Alerta(tipo, this);
        nuevaAlerta.setId(RandomGenerator.getDefault().nextInt(0,100));
        this.notificar(nuevaAlerta);
        PseudoBaseDatosAlerta.getInstance().agregar(nuevaAlerta);
    }


}

