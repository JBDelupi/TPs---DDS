package Models;

public class Direccion {
    private String calle;
    private String numero;
    private Localidad localidad;

    public Direccion(String calle, String numero, Localidad localidad) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
    }
}
