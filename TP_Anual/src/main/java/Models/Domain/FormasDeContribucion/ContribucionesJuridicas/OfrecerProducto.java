package Models.Domain.FormasDeContribucion.ContribucionesJuridicas;

import Models.Domain.FormasDeContribucion.Utilidades.Contribucion;
import Models.Domain.Producto.Producto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OfrecerProducto extends Contribucion {

    private Integer ID;
    private Producto producto;
    private Double puntosNecesarios;
    private Integer stock;

    public OfrecerProducto() {
        this.nombre = "Ofrecer producto";
    }
}
