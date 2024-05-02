package Models.Personas;

import Service.Validador.CredencialDeAcceso;
import lombok.Getter;

@Getter
public class Administrador extends Usuario{

    private String nombre;
    private String apellido;
    private String legajo;

    public Administrador(){
        this.setTipoRol(Rol.ADMINISTRADOR);
    }
}
