package Models.Domain.Builder.ContribucionBuilder;

import Models.Domain.FormasDeContribucion.OfrecerProducto;
import Models.Domain.FormasDeContribucion.TipoRubro;
import Models.Domain.Producto;

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
        return this.ofrecerProducto;
    }
}
