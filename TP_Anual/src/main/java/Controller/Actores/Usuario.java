package Controller.Actores;


import Service.Validador.CredencialDeAcceso;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public abstract class Usuario {
    private Rol rol;
    private CredencialDeAcceso credencialDeAcceso;

}

