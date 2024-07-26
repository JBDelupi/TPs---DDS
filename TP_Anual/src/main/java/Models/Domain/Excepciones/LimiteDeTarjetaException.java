package Models.Domain.Excepciones;

public class LimiteDeTarjetaException extends RuntimeException{

    public LimiteDeTarjetaException(String mensaje){
        super(mensaje);
    }

}
