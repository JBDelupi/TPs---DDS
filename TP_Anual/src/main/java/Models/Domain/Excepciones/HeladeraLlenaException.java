package Models.Domain.Excepciones;

public class HeladeraLlenaException extends RuntimeException{
    public HeladeraLlenaException(String mensaje){
        super(mensaje);
    }
}
