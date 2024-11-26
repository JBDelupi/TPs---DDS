package Models.Domain.Excepciones;

public class CapacidadHeladeraIgualACeroException extends RuntimeException{
    public CapacidadHeladeraIgualACeroException(String mensaje){
        super(mensaje);
    }
}
