package Models.Domain.Producto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Producto
{
    private int id;
    private TipoRubro rubro;
    private String nombre;
    private String imagen;
    private String descripcion;

    public Producto(TipoRubro rubro, String nombre, String imagen, String descripcion){
        this.rubro = rubro;
        this.nombre = nombre;
        this.imagen = imagen;
        this.descripcion = descripcion;
    }


}
