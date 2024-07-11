package Controller.Actores;

public class Permiso {

    private String nombre;


    private String nombreInterno;

    public boolean coincideConNombreInterno(String nombre) {
        return this.nombreInterno.equals(nombre);
    }
}
