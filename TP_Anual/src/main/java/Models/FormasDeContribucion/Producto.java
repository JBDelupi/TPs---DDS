package Models.FormasDeContribucion;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Producto extends FormaDeContribucion{

    private TipoRubro rubro;
    private String nombre;
    private Double puntosNecesarios;
    private Integer cantidad;
    private String imagen;

    // POSIBLEMENTE SEPARAR PRODUCTO DE CANTIDAD
    // VER COMO AGREGAR PRODUCTO A LA LISTA DE PRODUCTOS DE PERSONA JURIDICA. AHORA ESTA AGREGANDOLO A LA LISTA DE CONTRIBUCIONES.

}
