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
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.random.RandomGenerator;

@Getter
@Setter

@Entity
@Table(name = "Heladera")

public class Heladera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private Direccion direccion;


    @Column(name = "capacidad_de_viandas")
    private int capacidadDeViandas;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_de_puesta_en_marcha")
    private LocalDate fechaDePuestaEnMarcha;

    @Enumerated(EnumType.STRING)
    private EstadoHeladera estadoActual;

    @Column(name = "temperatura_maxima")
    private Double temperaturaMax;

    @Column(name = "temperatura_minima")//Celsius
    private Double temperaturaMin;

    @Convert(converter = org.hibernate.type.TrueFalseConverter.class)
    private Boolean abierto;

    @Transient
    private List<Vianda> viandas;

    @Convert(converter = org.hibernate.type.TrueFalseConverter.class)
    private Boolean estaLlena = false;

    @Transient
    private Sensor sensorMovimiento;

    @Transient
    private Sensor sensorTemperatura;

    @Column(name = "temperatura_actual")
    private Double temperaturaActual;

    @Column(name = "cantidad_de_fallas")
    private Integer cantidadDeFallas;

    @Column(name = "cantidad_de_viandas_retiradas")
    private Integer cantidadDeviandasRetiradas;

    @Column(name = "cantidad_de_viandas_depositadas")
    private Integer cantidadDeviandasDepositadas;

    @Transient
    private List<ObserverHeladera> suscriptores;

    @Transient
    private Persona responsable;

    public Heladera() {
        this.viandas = new ArrayList<>();
        this.suscriptores = new ArrayList<>();
        this.sensorMovimiento = new SensorMovimiento(this);
        this.sensorTemperatura = new SensorTemperatura(this);
        this.setEstadoActual(EstadoHeladera.DISPONIBLE);
        this.cantidadDeFallas = 0;
        this.fechaDePuestaEnMarcha = LocalDate.now();
        this.cantidadDeviandasRetiradas = 0;
        this.cantidadDeviandasDepositadas = 0;
    }

    public void agregarVianda(Vianda ... vianda) {

        if ( capacidadDeViandas == viandas.size() ) {
            this.estaLlena = true;
            throw new HeladeraLlenaException("Esta llena la heladera");
        }
        registrarViandaDepositada();
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
        registrarViandaRetirada();
        return vianda;

    }

    public void registrarAlerta(){ this.cantidadDeFallas++; }
    public void reestablecerFallas() { this.cantidadDeFallas = 0; }

    public void registrarViandaDepositada(){this.cantidadDeviandasDepositadas++;}
    public void registrarViandaRetirada(){ this.cantidadDeviandasRetiradas++; }
    public void reestablecerViandasRetiradas(){ this.cantidadDeviandasRetiradas = 0; }
    public void reestablecerViandasDepositadas(){ this.cantidadDeviandasDepositadas = 0; }



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
        registrarAlerta();
    }


}

