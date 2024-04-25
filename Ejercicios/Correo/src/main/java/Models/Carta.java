package Models;


public class Carta extends Envio{
    private TipoCarta tipo;
    private Boolean selloRojo;

    public Carta(Boolean selloRojo,TipoCarta tipo, Cliente destinatario, Cliente remitente, Double precio){
        this.selloRojo = selloRojo;
        this.tipo = tipo;
        this.setDestinatario(destinatario);
        this.setRemitente(remitente);
        this.setPrecio(precio);
        this.agregarNuevoEstado( this.estadoInicial() );
    }
}
