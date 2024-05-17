package Models.Tarjeta;

import Models.Personas.Humano;
import Models.Personas.PersonaVulnerable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public void agregarNuevoUso(RegistroDeUso uso){
        this.getUsosHoy();
        if(this.usosHoy < this.cantMaxUso ){
            usos.add(uso);
            this.usosHoy++;
            System.out.println("Uso exitoso tarjeta cantidad disponible: " +  (cantMaxUso - usosHoy) );
        } else {
            System.out.println("Cantidad limite usada");
        }

    }

    public void getUsosHoy() {
        LocalDate hoy = LocalDate.now();
        if (! hoy.equals( this.fechaUltUso ) ) {
            this.fechaUltUso = hoy;
            this.usosHoy = 0;
        }
    }

}
