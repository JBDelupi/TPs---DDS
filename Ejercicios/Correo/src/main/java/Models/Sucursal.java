package Models;

import java.util.List;

public class Sucursal {
    private Integer id;
    private String nombre;
    private Direccion direccion;
    private List<Administrativo> administrativos;
    private List<Cartero> cartero;

    public Sucursal(Integer id, String nombre, Direccion direccion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public void agregarEmpleadoAdministrativo(Administrativo administrativo) {
        this.administrativos.add(administrativo);
    }

    public void agregarEmpleadoCartero(Cartero cartero) {
        this.cartero.add(cartero);
    }

}
