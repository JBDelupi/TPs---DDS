package Models.Domain.Incidentes;

public class Alerta extends Incidente {
    private TipoAlerta tipo;

    public Alerta(TipoAlerta tipo) {
        this.tipo = tipo;
    }

    @Override
    public void notificar() {
        System.out.println("PELIGRO! Alerta de tipo: " + tipo);
    }
}
