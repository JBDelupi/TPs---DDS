package Models.Domain.Excepciones;

public class UsuarioYaTieneRolException extends RuntimeException{
    public UsuarioYaTieneRolException(String mensaje){
        super(mensaje);
    }
}
