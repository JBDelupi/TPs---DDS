package Models.Domain.Excepciones;

public class SinViandasException extends RuntimeException{

    public SinViandasException(String mensaje){
        super(mensaje);
    }

}
