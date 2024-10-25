package Models.Domain.Producto;

import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.OfrecerProducto;
import Models.Domain.Personas.Actores.Colaborador;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Getter

@Entity
@Table(name = "Canje")
@NoArgsConstructor

public class Canje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    private OfrecerProducto ofrecerProducto; // Lo que canjeo

    @ManyToOne()
    private Colaborador colaborador; // El que hizo el canje

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_canje")
    private LocalDate fecha; // El que hizo el canje

    @Column(name = "cantidad")
    private Integer cantidad;

    public Canje(OfrecerProducto producto, Colaborador colaborador, Integer cantidad) {
        this.ofrecerProducto = producto;
        this.colaborador = colaborador;
        this.fecha = LocalDate.now();
        this.cantidad = cantidad;
    }

}
