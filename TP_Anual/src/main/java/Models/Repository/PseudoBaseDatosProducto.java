package Models.Repository;

import Models.Domain.Producto.Producto;
import Models.Domain.Producto.TipoRubro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PseudoBaseDatosProducto {
    private Producto producto1;
    private Producto producto2;
    private Producto producto3;
    private Producto producto4;
    private Producto producto5;
    private static PseudoBaseDatosProducto instancia = null;

    public List<Producto> baseProductos;

    public void agregar(Producto... p) {
        Collections.addAll(this.baseProductos, p);
    }

    private PseudoBaseDatosProducto() {
        producto1 = new Producto(TipoRubro.ELECTRONICA, "MacBook Pro", "/images/producto-test.png", "Portátil de alto rendimiento con procesador M1 y pantalla Retina de 13 pulgadas.");
        producto2 = new Producto(TipoRubro.ELECTRONICA, "Dell XPS 13", "/images/producto-test.png" ,"Ultrabook ligero con pantalla InfinityEdge y procesador Intel de 11ª generación.");
        producto3 = new Producto(TipoRubro.ELECTRONICA, "HP Spectre x360", "/images/producto-test.png" ,"Convertible 2-en-1 con pantalla táctil OLED 4K y diseño ultradelgado.");
        producto4 = new Producto(TipoRubro.ELECTRONICA, "Lenovo ThinkPad X1 Carbon", "/images/producto-test.png" ,"Portátil empresarial con durabilidad militar, teclado retroiluminado y seguridad avanzada.");
        producto5 = new Producto(TipoRubro.ELECTRONICA, "ASUS ROG Zephyrus G14", "/images/producto-test.png" ,"Portátil gaming ultraligero con AMD Ryzen 9 y tarjeta gráfica NVIDIA GeForce RTX 3060.");
        this.baseProductos = new ArrayList<>();
        agregar(producto1, producto2, producto3, producto4, producto5);
        for (int i = 0; i < baseProductos.size(); i++) {
            baseProductos.get(i).setId(i + 1);
        }
    }

    public static PseudoBaseDatosProducto getInstance() {
        if (instancia == null) {
            instancia = new PseudoBaseDatosProducto();
        }
        return instancia;
    }

    public Producto getId(String id) {
        return baseProductos.stream()
                .filter(f -> f.getId() == Integer.parseInt(id))
                .findFirst()
                .get();
    }
}
