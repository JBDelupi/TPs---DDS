package Models.Domain.FormasDeContribucion.ContribucionesJuridicas;

import Models.Domain.FormasDeContribucion.Utilidades.FormaDeContribucion;
import Models.Domain.Producto.Producto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OfrecerProducto extends FormaDeContribucion {
    private Producto producto;
    private Double puntosNecesarios;
    private Integer stock;

}
