package Models.Domain.FormasDeContribucion.ContribucionesJuridicas;

import Models.Domain.FormasDeContribucion.Utilidades.Contribucion;
import Models.Domain.Producto.Producto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

@Entity
@DiscriminatorValue("ofrecer_producto")
public class OfrecerProducto extends Contribucion {

    @OneToOne(cascade = {CascadeType.ALL})
    private Producto producto;

    @Column(name = "puntos_necesarios")
    private Double puntosNecesarios;

    @Column(name = "stock")
    private Integer stock;

    public OfrecerProducto() {
        this.nombre = "Ofrecer producto";
    }

    public String getDetalle(){
        String unDetalle = " ";
        unDetalle += "Producto: " + this.getProducto().getNombre();
        unDetalle += ", Puntos Necesarios: " + this.getPuntosNecesarios().toString();
        unDetalle += ", Stock: " + this.getStock().toString();

        return unDetalle;
    }

}
