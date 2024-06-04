package Models;

import Models.FormasDeContribucion.Producto;
import Models.Personas.Colaborador;
import lombok.Getter;

import java.time.LocalDate;
@Getter
public class Canje {
    private Producto producto; // Lo que canjeo
    private Colaborador colaborador; // El que hizo el canje
    private LocalDate fecha; // El que hizo el canje

    public Canje(Producto producto, Colaborador colaborador) {
        this.producto = producto;
        this.colaborador = colaborador;
        this.fecha = LocalDate.now();
    }

}
