package Models.Repository;

import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.OfrecerProducto;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Producto.Producto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PseudoBaseDatosProducto{
    private static PseudoBaseDatosProducto instacia = null;


    public List<Producto> baseProductos;

    public void agregar(Producto... p) {
        Collections.addAll(this.baseProductos, p);
    }

    private PseudoBaseDatosProducto(){
        this.baseProductos = new ArrayList<>();
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
