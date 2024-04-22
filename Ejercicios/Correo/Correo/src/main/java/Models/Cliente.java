package Models;

public class Cliente {
    private String nombre;
    private Direccion direccion;
    private String tipoCliente;

    public Cliente(String nombre, Direccion direccion, String tipoCliente) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.tipoCliente = tipoCliente;
    }
}
