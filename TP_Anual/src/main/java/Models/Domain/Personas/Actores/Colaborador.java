package Models.Domain.Personas.Actores;

import Models.Domain.Excepciones.NoTienePuntosCanjeException;
import Models.Domain.Producto.Canje;
import Models.Domain.FormasDeContribucion.Utilidades.Contribucion;
import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.OfrecerProducto;
import Models.Domain.Tarjetas.TarjetaAccesos;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Colaborador extends Rol {
    private List<Contribucion> contribuciones;
    private List<Canje> historialCanje;
    private Double puntaje;
    private TarjetaAccesos tarjeta; // Persistencia se elimina, para evitar bidireccionaldiad
    private int cantidadViandasDonadas;

    public Colaborador(){
        this.tipo = TipoRol.COLABORADOR;
    }

    public void agregarNuevaDonacion(Contribucion unaDonacion){
        this.contribuciones.add(unaDonacion);
        this.sumarPuntaje(unaDonacion);

    }

    private void sumarPuntaje(Contribucion unaDonacion){
        puntaje += unaDonacion.generarPuntaje();
    }


    public void realizarCanje(OfrecerProducto producto, Integer cantidad){
        if (!(puntaje >= producto.getPuntosNecesarios()*cantidad && cantidad <=producto.getStock())){
            throw new NoTienePuntosCanjeException("No tiene suficiente Puntos");
        }
        puntaje -= producto.getPuntosNecesarios() * cantidad;
        producto.setStock(producto.getStock() - cantidad);
        Canje canje = new Canje(producto, this, cantidad);
        historialCanje.add(canje);

    }

    public void reestablecerViandas(){this.cantidadViandasDonadas = 0;}

}
