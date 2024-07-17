package Models.Domain.Tarjetas;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Humano;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.Actores.PersonaVulnerable;
import Models.Domain.Heladera.Vianda;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class TarjetaPersonaVulnerable extends Tarjeta {
    private Humano colaborador;
    private Integer cantMaxUso;
    private Integer usosHoy;
    private LocalDate fechaUltUso;

    public TarjetaPersonaVulnerable(Persona titular){
        this.titular = titular;
        this.cantMaxUso = 4 + 2 * ((PersonaVulnerable)getTitular()).getMenoresACargo();
        this.fechaUltUso = LocalDate.now();
        this.usosHoy = 0;
    }

    public void agregarNuevoUso(Heladera heladera){
        this.calcularUsosHoy();
        if(this.usosHoy < this.cantMaxUso ){
            Vianda viandaConsume = heladera.obtenerVianda();
            RegistroDeUso unNuevoUso = new RegistroDeUso(heladera,viandaConsume,TipoAccion.QUITAR);
            getUsos().add(unNuevoUso);
            this.usosHoy++;
            System.out.println("Uso exitoso tarjeta cantidad disponible: " +  (cantMaxUso - usosHoy) );
        } else {
            System.out.println("Cantidad limite usada");
        }

    }

    public void calcularUsosHoy() {
        LocalDate hoy = LocalDate.now();
        if (! hoy.equals( this.fechaUltUso ) ) {
            this.fechaUltUso = hoy;
            this.usosHoy = 0;
        }
    }
}
