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
import Models.Repository.EntityManager.EntityManagerHelper;
import Service.Notificacion.Mensaje.MensajeAlerta;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @OneToMany()
    @JoinColumn(name = "id_heladera") // CLAVE FORANEA
    private List<Vianda> viandas;

    @Convert(converter = org.hibernate.type.TrueFalseConverter.class)
    private Boolean estaLlena;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_heladera") // CLAVE FORANEA
    private List<ObserverHeladera> suscriptores;

    @ManyToOne()
    @JoinColumn(referencedColumnName = "id", name = "persona_responsable_id")
    private Persona responsable;

    @Column(name = "capacidadActual")
    private int capacidadActual;

    public Heladera() {
        this.viandas = new ArrayList<>();
        this.suscriptores = new ArrayList<>();
        this.sensorMovimiento = new SensorMovimiento(this);
        this.sensorTemperatura = new SensorTemperatura(this);
        this.setEstadoActual(EstadoHeladera.DISPONIBLE);
        this.estaLlena = false;
        this.cantidadDeFallas = 0;
        this.fechaDePuestaEnMarcha = LocalDate.now();
        this.cantidadDeviandasRetiradas = 0;
        this.cantidadDeviandasDepositadas = 0;
        this.capacidadActual = this.capacidadDeViandas;

    }

    public void agregarVianda(Vianda ... vianda) {
        if (estaLlena) {
            throw new HeladeraLlenaException("Esta llena la heladera");
        }
        registrarViandaDepositada();
        Collections.addAll(this.viandas, vianda);
        this.capacidadActual --;
        this.registrarViandaDepositada();
        generarNuevaPublicacion(TipoDePublicacion.FALTAN_N_VIANDAS);
        if ( capacidadDeViandas == viandas.size() ) {
            this.estaLlena = true;
        }
    }


    public Vianda obtenerVianda() {
        if (viandas.isEmpty()) {
            throw new SinViandasException("No hay viandas");
        }

        if (estaLlena) {
            estaLlena = false;
        }

        Vianda vianda = viandas.remove(0);
        capacidadActual++;

        sensorMovimiento.chequear();
        this.registrarViandaRetirada();

        generarNuevaPublicacion(TipoDePublicacion.N_VIANDAS_DISPONIBLES);
        registrarViandaRetirada();

        return vianda;
    }

    public void setTemperaturaActual(Double temperaturaActual) {
        this.temperaturaActual = temperaturaActual;
        this.sensorTemperatura.activar();
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
        this.registrarAlerta();
        this.notificar(nuevaAlerta);
        EntityManagerHelper.persist(nuevaAlerta);
        registrarAlerta();
    }


}

