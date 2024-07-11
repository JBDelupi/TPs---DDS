package Controller.Actores;


import Models.Domain.FormasDeContribucion.FormaDeContribucion;
import Service.Validador.CredencialDeAcceso;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public abstract class Usuario {
    private Rol rol;
    private CredencialDeAcceso credencialDeAcceso;

}

