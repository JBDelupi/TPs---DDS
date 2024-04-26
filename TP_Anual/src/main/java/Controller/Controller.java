package Controller;

import Models.Personas.Colaborador;
import lombok.Setter;

@Setter
public abstract class Controller {
    Colaborador colaborador;

    protected void checkUserRoleAndProceed(String tipoRol) {
        if(colaborador.getClass().getSimpleName() != tipoRol) {
            throw new Controller.UnauthorizedAccessException("El usuario no tiene el rol adecuado para realizar esta acción.");
        }

    }

    public class UnauthorizedAccessException extends RuntimeException {
        public UnauthorizedAccessException(String message) {
            super(message);
        }
    }
}
