package Models.Domain.Excepciones;

import Models.Domain.Personas.Actores.TipoRol;
import Models.Domain.Personas.Actores.Persona;

public class  Permisos {
    Persona persona;

    public void checkUserRoleAndProceed(TipoRol tipoRol) {
        if( persona.getRoles().stream().anyMatch(f->f.getTipo().compareTo(tipoRol) == 0)) {
            throw new UnauthorizedAccessException("El usuario no tiene el rol adecuado para realizar esta acción.");
        }

    }

    public Permisos(Persona persona) {
        this.persona = persona;
    }

    public static class UnauthorizedAccessException extends RuntimeException {
        public UnauthorizedAccessException(String message) {
            super(message);
        }
    }

}
