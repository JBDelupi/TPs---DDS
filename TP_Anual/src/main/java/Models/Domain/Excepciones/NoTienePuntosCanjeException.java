package Models.Domain.Excepciones;

public class NoTienePuntosCanjeException extends RuntimeException{

    public NoTienePuntosCanjeException(String mensaje){
        super(mensaje);
    }
}
