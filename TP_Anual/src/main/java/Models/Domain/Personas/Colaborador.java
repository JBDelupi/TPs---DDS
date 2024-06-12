package Models.Domain.Personas;

import Controller.Actores.Usuario;
import Models.Domain.Canje;
import Models.Domain.Direccion;
import Models.Domain.FormasDeContribucion.FormaDeContribucion;
import Models.Domain.FormasDeContribucion.Producto;
import Models.Domain.MedioDeNotificacion;
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

    public void realizarCanje(Producto producto, Integer cantidad){
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
/*

[Cantidad] -> [1,2,3]


*/