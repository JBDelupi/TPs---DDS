package Models;


import java.util.List;

public class Giro extends Envio{
    private Double importe;

    public Giro(double importe, Cliente destinatario, Cliente remitente, Double precio){
        this.importe = importe;
        this.setDestinatario(destinatario);
        this.setRemitente(remitente);
        this.setPrecio(precio);
        this.agregarNuevoEstado( this.estadoInicial() );
    };
}
