package Models.Personas;


import Models.FormasDeContribucion.FormaDeContribucion;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public abstract class Usuario {
    private Rol tipoRol;
    public void agregarNuevaDonacion(FormaDeContribucion nuevaDonacion) {
    }
}
