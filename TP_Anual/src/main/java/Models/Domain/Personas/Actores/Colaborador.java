package Models.Domain.Personas.Actores;

import Models.Domain.Heladera.Suscripciones.Publicacion;
import Models.Domain.Producto.Canje;
import Models.Domain.DatosPersonales.Direccion;
import Models.Domain.FormasDeContribucion.Utilidades.FormaDeContribucion;
import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.OfrecerProducto;
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

    public void notify(Publicacion publicacion){
        String tipoPublicacion = publicacion.getTipoPublicacion().toString();
        String descripcion = publicacion.getDescripcion();
        Mensaje mensaje = new Mensaje();
        mensaje.setAsunto(tipoPublicacion);
        mensaje.setDestinatario(this.correoElectronico);
        mensaje.setContenido(descripcion);

        medioDeNotificacion.Notificar(mensaje);
    }


    public void realizarCanje(OfrecerProducto producto, Integer cantidad){
        if ((puntaje >= producto.getPuntosNecesarios()*cantidad && cantidad>=producto.getStock() ) ){
            puntaje -= producto.getPuntosNecesarios()*cantidad;
            producto.setStock(producto.getStock() - cantidad);
            // Decidir si eliminar el producto o mantenerlo pero con cantidad 0
            Canje canje = new Canje(producto,this, cantidad);
            historialCanje.add(canje);
        }
        else{
            System.out.println("No se pudo realizar el canje");
        }

    }

}
