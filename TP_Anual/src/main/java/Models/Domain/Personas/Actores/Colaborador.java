package Models.Domain.Personas.Actores;

import Models.Domain.Excepciones.NoTienePuntosCanjeException;
import Models.Domain.Producto.Canje;
import Models.Domain.Personas.DatosPersonales.Direccion;
import Models.Domain.FormasDeContribucion.Utilidades.FormaDeContribucion;
import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.OfrecerProducto;
import Models.Domain.Tarjetas.SolicitudDeApertura;
import Models.Domain.Tarjetas.TarjetaAccesosAHeladera;
import Service.Notificacion.Mensaje;
import Service.Notificacion.Notificacion;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class Colaborador extends Persona {
    private Notificacion medioDeNotificacion;
    private Direccion direccion;
    private List<FormaDeContribucion> formaDeContribucion;
    private List<Canje> historialCanje;
    private Double puntaje;
    private String correoElectronico;
    private TarjetaAccesosAHeladera tarjeta; // Persistencia se elimina, para evitar bidireccionaldiad

    public Colaborador(){
        this.formaDeContribucion = new ArrayList<>();
        this.puntaje = 0.0;
        this.historialCanje = new ArrayList<>();
    }

    public void agregarNuevaDonacion(FormaDeContribucion unaDonacion){
        this.formaDeContribucion.add(unaDonacion);
    }


    public void generarNuevaDonacion(FormaDeContribucion unaDonacion) {
        this.agregarNuevaDonacion(unaDonacion);
        sumarPuntaje(unaDonacion);
    }

    public void sumarPuntaje(FormaDeContribucion unaDonacion){
        puntaje += unaDonacion.generarPuntaje();
    }

    public void notify(Mensaje publicacion){
       medioDeNotificacion.Notificar(publicacion);
    }


    public void realizarCanje(OfrecerProducto producto, Integer cantidad){
        if (!(puntaje >= producto.getPuntosNecesarios()*cantidad &&  cantidad >=producto.getStock())){
            throw new NoTienePuntosCanjeException("No tiene suficiente Puntos");
        }
        puntaje -= producto.getPuntosNecesarios() * cantidad;
        producto.setStock(producto.getStock() - cantidad);
        Canje canje = new Canje(producto, this, cantidad);
        historialCanje.add(canje);

    }


}
