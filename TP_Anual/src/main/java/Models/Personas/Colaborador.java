package Models.Personas;

import Controller.Actores.Usuario;
import Models.Canje;
import Models.Direccion;
import Models.FormasDeContribucion.FormaDeContribucion;
import Models.FormasDeContribucion.Producto;
import Models.MedioDeNotificacion;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class Colaborador extends Usuario {
    private MedioDeNotificacion medioDeNotificacion;
    private Direccion direccion;
    private List<FormaDeContribucion> formaDeContribucion;
    private List<Canje> historialCanje;
    private Double puntaje;


    public Colaborador(){
        this.formaDeContribucion = new ArrayList<>();
        this.puntaje = 0.0;
        this.historialCanje = new ArrayList<>();
    }

    public void agregarNuevaDonacion(FormaDeContribucion unaDonacion) {
        formaDeContribucion.add(unaDonacion);
        sumarPuntaje(unaDonacion);
    }

    public void sumarPuntaje(FormaDeContribucion unaDonacion){
        puntaje =+ unaDonacion.generarPuntaje();
    }

    public void realizarCanje(Producto producto, Integer cantidad){
        if ((puntaje >= producto.getPuntosNecesarios() && cantidad>=producto.getCantidad())){
            puntaje -= producto.getPuntosNecesarios();
            producto.setCantidad(producto.getCantidad() - cantidad);
            // Decidir si eliminar el producto o mantenerlo pero con cantidad 0
            Canje canje = new Canje(producto,this);
            historialCanje.add(canje);
        }
        else{
            System.out.println("No se pudo realizar el canje");
        }
    }
}
