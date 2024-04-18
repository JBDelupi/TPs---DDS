package Models;

import Models.FormasDeContribucion.DonacionDeDinero;
import Models.FormasDeContribucion.FormaDeContribucion;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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