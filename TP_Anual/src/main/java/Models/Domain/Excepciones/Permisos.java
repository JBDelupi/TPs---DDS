package Models.Domain.Excepciones;

import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.Utilidades.TipoRolNegocio;

public class Permisos {
    Persona persona;
    public void checkUserRoleAndProceed(TipoRolNegocio tipoRol) {
        if(persona.getRolNegocio() != tipoRol) {
            throw new Permisos.UnauthorizedAccessException("El usuario no tiene el rol adecuado para realizar esta acción.");
        }

    }

    public Permisos(Persona persona) {
        this.persona = persona;
    }

    public class UnauthorizedAccessException extends RuntimeException {
        public UnauthorizedAccessException(String message) {
            super(message);
        }
    }

}
