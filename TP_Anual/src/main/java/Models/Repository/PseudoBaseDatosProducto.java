package Models.Repository;

import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.OfrecerProducto;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Producto.Producto;
import Models.Domain.Producto.TipoRubro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PseudoBaseDatosProducto{
    private Producto producto1;
    private Producto producto2;
    private Producto producto3;
    private Producto producto4;
    private Producto producto5;
    private static PseudoBaseDatosProducto instacia = null;


    public List<Producto> baseProductos;

    public void agregar(Producto... p) {
        Collections.addAll(this.baseProductos, p);
    }

    private PseudoBaseDatosProducto(){
        producto1 = new Producto(TipoRubro.ELECTRONICA, "Laptop1", "");
        producto2 = new Producto(TipoRubro.ELECTRONICA, "Laptop2", "");
        producto3 = new Producto(TipoRubro.ELECTRONICA, "Laptop3", "");
        producto4 = new Producto(TipoRubro.ELECTRONICA, "Laptop4", "");
        producto5 = new Producto(TipoRubro.ELECTRONICA, "Laptop5", "");
        this.baseProductos = new ArrayList<>();
        agregar(producto1, producto2, producto3, producto4, producto5);
    }
    public static PseudoBaseDatosProducto getInstance() {
        if(instacia==null){
            instacia = new PseudoBaseDatosProducto();
        }
        return instacia;
    }


    public Producto getId(String id){
        return baseProductos.stream().filter(f-> f.getId() == Integer.parseInt(id)).findFirst().get();
    }
}
