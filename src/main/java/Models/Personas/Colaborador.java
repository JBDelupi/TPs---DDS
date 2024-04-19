package Models.Personas;

import Models.Direccion;
import Models.FormasDeContribucion.FormaDeContribucion;
import Models.MedioDeNotificacion;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class Colaborador {
    private MedioDeNotificacion medioDeNotificacion;
    private Direccion direccion;
    private List<FormaDeContribucion> formaDeContribucion;

    public Colaborador(){
        this.formaDeContribucion = new ArrayList<>();
    }

    public void agregarNuevaDonacion(FormaDeContribucion unaDonacion) {
        formaDeContribucion.add(unaDonacion);
    }
}
