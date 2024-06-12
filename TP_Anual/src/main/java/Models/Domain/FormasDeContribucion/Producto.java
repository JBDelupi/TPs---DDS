package Models.Domain.FormasDeContribucion;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Producto extends FormaDeContribucion{

    private TipoRubro rubro;
    private String nombre;
    private Double puntosNecesarios;
    private Integer stock;
    private String imagen;

    public Producto (TipoRubro rubro, String nombre, Double puntosNecesarios, Integer cantidad, String imagen) {
        this.rubro = rubro;
        this.nombre = nombre;
        this.puntosNecesarios = puntosNecesarios;
        this.stock = cantidad;
        this.imagen = imagen;
    }



}
