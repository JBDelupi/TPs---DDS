package Models.Repository;

import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.OfrecerProducto;
import Models.Domain.Heladera.Heladera;

import java.util.Collections;
import java.util.List;

public class PseudoBaseDatosProducto{
    private OfrecerProducto producto1;
    private OfrecerProducto producto2;
    private OfrecerProducto producto3;
    private OfrecerProducto producto4;
    private OfrecerProducto producto5;
    private static PseudoBaseDatosProducto instacia = null;


    public List<OfrecerProducto> baseProductos;

    public void agregar(OfrecerProducto... p) {
        Collections.addAll(this.baseProductos, p);
    }

    public static PseudoBaseDatosProducto getInstance() {
        if(instacia==null){
            instacia = new PseudoBaseDatosProducto();
        }
        return instacia;
    }

    public OfrecerProducto getId(String id){
        return baseProductos.stream().filter(f->f.getID().equals(id)).findFirst().get();
    }
}
