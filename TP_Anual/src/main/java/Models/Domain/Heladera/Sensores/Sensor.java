package Models.Domain.Heladera.Sensores;

public interface Sensor {

    public void activar();
    public void desactivar();

    public void notificar();

    public void chequear();

}
