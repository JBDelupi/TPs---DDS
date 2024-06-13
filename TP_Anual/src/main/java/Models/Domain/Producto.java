package Models.Domain;

import Models.Domain.FormasDeContribucion.TipoRubro;
import lombok.Getter;

@Getter
public class Producto
{
    private TipoRubro rubro;
    private String nombre;
    private String imagen;

    public Producto(TipoRubro rubro, String nombre, String imagen){
        this.rubro = rubro;
        this.nombre = nombre;
        this.imagen = imagen;
    }

}
