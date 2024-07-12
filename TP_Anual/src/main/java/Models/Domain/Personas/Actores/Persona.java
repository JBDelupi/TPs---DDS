package Models.Domain.Personas.Actores;


import Controller.Actores.Usuario;
import Models.Domain.Personas.Utilidades.TipoRolNegocio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Persona extends Usuario {
    private TipoRolNegocio rolNegocio;

}
