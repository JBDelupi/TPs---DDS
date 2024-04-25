package Models;


public class Encomienda extends Envio {
    private TipoEncomienda tipo;

    public Encomienda(TipoEncomienda tipo, Cliente destinatario, Cliente remitente, Double precio){
        this.tipo = tipo;
        this.setDestinatario(destinatario);
        this.setRemitente(remitente);
        this.setPrecio(precio);
        this.agregarNuevoEstado( this.estadoInicial() );

    }
}
