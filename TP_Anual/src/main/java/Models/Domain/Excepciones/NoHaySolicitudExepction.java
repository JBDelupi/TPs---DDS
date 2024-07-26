package Models.Domain.Excepciones;

public class NoHaySolicitudExepction extends RuntimeException{
    public NoHaySolicitudExepction(String mensaje){
        super(mensaje);
    }
}
