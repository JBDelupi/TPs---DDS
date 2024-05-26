package Controller;

import Controller.Actores.Rol;
import Controller.Actores.Usuario;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Controller {
    Usuario usuario;


    protected void checkUserRoleAndProceed(Rol tipoRol) {
        if(usuario.getTipoRol() != tipoRol) {
            throw new Controller.UnauthorizedAccessException("El usuario no tiene el rol adecuado para realizar esta acción.");
        }

    }

    public class UnauthorizedAccessException extends RuntimeException {
        public UnauthorizedAccessException(String message) {
            super(message);
        }
    }

    public abstract void create(Object... Args);


}
