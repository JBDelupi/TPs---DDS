package Models.Domain.Personas;


import Controller.Actores.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Persona extends Usuario {
    private TipoRolNegocio rolNegocio;



}
