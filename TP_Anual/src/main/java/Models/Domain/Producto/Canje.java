package Models.Domain.Producto;

import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.OfrecerProducto;
import Models.Domain.Personas.Actores.Colaborador;
import lombok.Getter;

import java.time.LocalDate;
@Getter
public class Canje {
    private OfrecerProducto ofrecerProducto; // Lo que canjeo
    private Colaborador colaborador; // El que hizo el canje
    private LocalDate fecha; // El que hizo el canje
    private Integer cantidad;

    public Canje(OfrecerProducto producto, Colaborador colaborador, Integer cantidad) {
        this.ofrecerProducto = producto;
        this.colaborador = colaborador;
        this.fecha = LocalDate.now();
        this.cantidad = cantidad;
    }

}
