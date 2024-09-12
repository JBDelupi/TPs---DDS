package Models.Repository;

import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.OfrecerProducto;
import Models.Domain.Producto.Producto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PseudoBaseDatosProductosOfrecidos {
    private static PseudoBaseDatosProductosOfrecidos instancia = null;

    private List<OfrecerProducto> baseProductosOfrecidos;

    private PseudoBaseDatosProductosOfrecidos() {
        this.baseProductosOfrecidos = new ArrayList<>();

        // Obtener la instancia de la pseudo base de datos de productos
        PseudoBaseDatosProducto baseDeDatosProductos = PseudoBaseDatosProducto.getInstance();

        // Crear OfrecerProducto a partir de los productos existentes en la base de datos
        agregarProducto(baseDeDatosProductos.getId("1"), 2000.0, 10);
        agregarProducto(baseDeDatosProductos.getId("2"), 1800.0, 8);
        agregarProducto(baseDeDatosProductos.getId("3"), 2500.0, 5);
        agregarProducto(baseDeDatosProductos.getId("4"), 1500.0, 12);
        agregarProducto(baseDeDatosProductos.getId("5"), 3000.0, 7);
    }

    private void agregarProducto(Producto producto, Double puntosNecesarios, Integer stock) {
        OfrecerProducto ofrecerProducto = new OfrecerProducto();
        ofrecerProducto.setID(producto.getId());
        ofrecerProducto.setProducto(producto);
        ofrecerProducto.setPuntosNecesarios(puntosNecesarios);
        ofrecerProducto.setStock(stock);
        this.agregar(ofrecerProducto);
    }

    public static PseudoBaseDatosProductosOfrecidos getInstance() {
        if (instancia == null) {
            instancia = new PseudoBaseDatosProductosOfrecidos();
        }
        return instancia;
    }

    public void agregar(OfrecerProducto... productosOfrecidos) {
        Collections.addAll(this.baseProductosOfrecidos, productosOfrecidos);
    }

    public List<OfrecerProducto> getBaseProductosOfrecidos() {
        return baseProductosOfrecidos;
    }

    public OfrecerProducto getOfrecerProductoById(String id) {
        return baseProductosOfrecidos.stream()
                .filter(p -> p.getID() == Integer.parseInt(id))
                .findFirst()
                .orElse(null);
    }
}
