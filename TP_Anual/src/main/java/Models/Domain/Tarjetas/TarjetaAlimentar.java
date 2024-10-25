package Models.Domain.Tarjetas;

import Models.Domain.Personas.Actores.TipoRol;
import Models.Domain.Excepciones.LimiteDeTarjetaException;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Fisico;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.Actores.PersonaVulnerable;
import Models.Domain.Heladera.Vianda;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;

@Setter
@Getter

@Entity
@DiscriminatorValue("tarjeta_alimentar")
@NoArgsConstructor

public class TarjetaAlimentar extends Tarjeta {
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(referencedColumnName = "id", name = "colaborador_id")
    private Fisico colaborador;

    @Column(name = "cant_maxima_uso")
    private Integer cantMaxUso;

    @Column(name = "usos_hoy")
    private Integer usosHoy;

    @Temporal(TemporalType.DATE)
    private LocalDate fechaUltUso;

    public TarjetaAlimentar(Persona titular){
        this.titular = titular;
        ((PersonaVulnerable)this.titular.getRol(TipoRol.VULNERABLE)).setTarjeta(this);
        this.cantMaxUso = 4 + 2 * ((PersonaVulnerable)getTitular().getRol(TipoRol.VULNERABLE)).getMenoresACargo();
        this.fechaUltUso = LocalDate.now();
        this.usosHoy = 0;
        this.usos = new ArrayList<>();
    }

    public void agregarNuevoUso(Heladera heladera, TipoAccion tipoAccion){
        this.calcularUsosHoy();

        if (this.usosHoy >= this.cantMaxUso) {
            throw new LimiteDeTarjetaException("Cantidad límite usada");
        }

        Vianda viandaConsume = heladera.obtenerVianda();
        RegistroDeUso unNuevoUso = new RegistroDeUso(heladera,viandaConsume,tipoAccion);
        nuevoRegistro(unNuevoUso);
        this.usosHoy++;
    }

    public void calcularUsosHoy() {
        LocalDate hoy = LocalDate.now();
        if (! hoy.equals( this.fechaUltUso ) ) {
            this.fechaUltUso = hoy;
            this.usosHoy = 0;
        }
    }
}
