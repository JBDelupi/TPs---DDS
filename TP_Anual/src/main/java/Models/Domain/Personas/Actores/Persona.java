package Models.Domain.Personas.Actores;


import Models.Domain.Personas.DatosPersonales.Direccion;
import Service.Notificacion.Mensaje;
import Service.Notificacion.Notificacion;
import Service.Validador.CredencialDeAcceso;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class Persona  {
    private CredencialDeAcceso credencialDeAcceso;
    private Integer id;
    private Direccion direccion;
    private Notificacion medioDeNotificacion;
    private String correElectronico;
    private List<Rol> roles;

    public Persona() {
        this.roles = new ArrayList<>();
    }

    public void notify(Mensaje publicacion){
        medioDeNotificacion.Notificar(publicacion);
    }

    public boolean checkRol(TipoRol rol){
       return roles.stream().anyMatch(unRol->unRol.getTipo().equals(rol));
    }

    public Rol getRol(TipoRol rol){
        return roles.stream().filter(unRol->unRol.getTipo().equals(rol)).findFirst().orElse(null);
    }

    public void agregarRol(Rol rol){
        roles.add(rol);
    }

}
