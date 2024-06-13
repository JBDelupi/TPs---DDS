package Models.Domain.FormasDeContribucion;

import Models.Domain.Producto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OfrecerProducto extends FormaDeContribucion{
    private Producto producto;
    private Double puntosNecesarios;
    private Integer stock;


    public OfrecerProducto() {
    }



}
