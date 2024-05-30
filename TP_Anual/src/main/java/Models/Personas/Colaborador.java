package Models.Personas;

import Controller.Actores.Usuario;
import Models.Direccion;
import Models.FormasDeContribucion.FormaDeContribucion;
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
    private Double puntaje;


    public Colaborador(){
        this.formaDeContribucion = new ArrayList<>();
        this.puntaje = 0.0;
    }

    public void agregarNuevaDonacion(FormaDeContribucion unaDonacion) {
        formaDeContribucion.add(unaDonacion);
        calcularPuntaje(unaDonacion);
    }

    public void calcularPuntaje(FormaDeContribucion unaDonacion){
        puntaje =+ unaDonacion.generarPuntaje();
    }

}
