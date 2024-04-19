package Models.Personas;

import Models.Personas.Colaborador;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class Humano extends Colaborador {
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;

    public Humano(String nombre, String apellido){
        this.nombre = nombre;
        this.apellido = apellido;

    }



}