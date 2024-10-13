package Models.Domain.Builder.ContribucionBuilder;

import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.OfrecerProducto;
import Models.Domain.Producto.Producto;

public class OfrecerProductoBuilder {
    private OfrecerProducto ofrecerProducto;

    public OfrecerProductoBuilder() {
        this.ofrecerProducto = new OfrecerProducto();
    }

    public OfrecerProductoBuilder producto(Producto producto) {
        this.ofrecerProducto.setProducto(producto);
        return this;
    }

    public OfrecerProductoBuilder puntosNecesarios(Double puntosNecesarios) {
        this.ofrecerProducto.setPuntosNecesarios(puntosNecesarios);
        return this;
    }

    public OfrecerProductoBuilder stock(Integer stock ) {
        this.ofrecerProducto.setStock(stock);
        return this;
    }


    public OfrecerProducto construir(){
        if(ofrecerProducto.getProducto().getImagen() == null){
            ofrecerProducto.getProducto().setImagen("/images/producto-test.png");
        }
        return this.ofrecerProducto;
    }
}
