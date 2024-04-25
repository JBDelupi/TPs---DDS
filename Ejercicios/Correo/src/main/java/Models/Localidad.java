package Models;

import lombok.Getter;

@Getter
public class Localidad {
    private String nombre;
    private String codigoPostal;

    public Localidad(String nombre, String codigoPostal) {
        this.nombre = nombre;
        this.codigoPostal = codigoPostal;
    }
}
