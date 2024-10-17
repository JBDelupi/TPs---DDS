package Models.Domain.Tarjetas;

import Models.Domain.Personas.Actores.TipoRol;
import Models.Domain.Excepciones.LimiteDeTarjetaException;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Fisico;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.Actores.PersonaVulnerable;
import Models.Domain.Heladera.Vianda;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;

@Setter
@Getter
public class TarjetaAlimentar extends Tarjeta {
    private Fisico colaborador;
    private Integer cantMaxUso;
    private Integer usosHoy;
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
