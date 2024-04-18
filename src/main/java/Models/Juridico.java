package Models;

public class Juridico extends Colaborador{
    private String razonSocial;
    private TipoJuridico tipoJuridico;

    public Juridico(String razonSocial, TipoJuridico tipoJuridico, Direccion direccion, MedioDeNotificacion medioDeNotificacion){
        this.tipoJuridico = tipoJuridico;
        this.razonSocial = razonSocial;
        this.setDireccion(direccion);
        this.setMedioDeNotificacion(medioDeNotificacion);
    }

}
