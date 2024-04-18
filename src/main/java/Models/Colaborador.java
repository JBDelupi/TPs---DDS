package Models;

import Models.FormasDeContribucion.FormaDeContribucion;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class Colaborador {
    private MedioDeNotificacion medioDeNotificacion;
    private Direccion direccion;
    private List<FormaDeContribucion> formaDeContribucion;


    public void agregarNuevaDonacion(FormaDeContribucion unaDonacion) {
        formaDeContribucion.add(unaDonacion);
    }
}
