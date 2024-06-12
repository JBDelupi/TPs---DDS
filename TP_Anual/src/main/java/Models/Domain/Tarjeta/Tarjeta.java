package Models.Domain.Tarjeta;

import Models.Domain.Heladera;
import Models.Domain.Personas.Humano;
import Models.Domain.Personas.PersonaVulnerable;
import Models.Domain.Vianda;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Tarjeta {
    private String codigo;
    private PersonaVulnerable titular;
    private Humano colaborador;
    private Integer cantMaxUso;
    private List<RegistroDeUso> usos;
    private Integer usosHoy;
    private LocalDate fechaUltUso;

    public Tarjeta(Humano colaborador, PersonaVulnerable titular){
        this.titular = titular;
        this.colaborador = colaborador;
        this.cantMaxUso = 4 + 2 * titular.getMenoresACargo();
        this.fechaUltUso = LocalDate.now();
        this.usosHoy = 0;
        this.usos = new ArrayList<>();
    }

    public void agregarNuevoUso(Heladera heladera){
        this.calcularUsosHoy();
        if(this.usosHoy < this.cantMaxUso ){
            Vianda viandaConsume = heladera.obtenerVianda();
            RegistroDeUso unNuevoUso = new RegistroDeUso(heladera,viandaConsume);
            usos.add(unNuevoUso);
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
