package Models.Domain.FormasDeContribucion.ContribucionesJuridicas;

import Models.Domain.FormasDeContribucion.Utilidades.Contribucion;
import Models.Domain.Producto.Producto;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

@Entity
@DiscriminatorValue("ofrecer_producto")
public class OfrecerProducto extends Contribucion {
    @Transient
    private Producto producto;

    @Column(name = "puntos_necesarios")
    private Double puntosNecesarios;

    @Column(name = "stock")
    private Integer stock;

    public OfrecerProducto() {
        this.nombre = "Ofrecer producto";
    }
}
