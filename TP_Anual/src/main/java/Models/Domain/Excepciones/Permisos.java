package Models.Domain.Excepciones;

import Models.Domain.Personas.Persona;
import Models.Domain.Personas.TipoRolNegocio;

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
