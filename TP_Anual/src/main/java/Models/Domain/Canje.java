package Models.Domain;

import Models.Domain.FormasDeContribucion.Producto;
import Models.Domain.Personas.Colaborador;
import lombok.Getter;

import java.time.LocalDate;
@Getter
public class Canje {
    private Producto producto; // Lo que canjeo
    private Colaborador colaborador; // El que hizo el canje
    private LocalDate fecha; // El que hizo el canje
    private Integer cantidad;

    public Canje(Producto producto, Colaborador colaborador, Integer cantidad) {
        this.producto = producto;
        this.colaborador = colaborador;
        this.fecha = LocalDate.now();
        this.cantidad = cantidad;
    }

}
