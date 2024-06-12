package Models.Domain.Personas;

import Controller.Actores.Rol;
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
        this.setTipoRol(Rol.HUMANO);

    }



}