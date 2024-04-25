package Models;

import java.util.List;

public class Telegrama extends Envio {
    private String texto;
    private String clase;

    public Telegrama(String texto, String clase, Cliente destinatario, Cliente remitente, Double precio){
            this.texto = texto;
            this.clase = clase;
            this.setDestinatario(destinatario);
            this.setRemitente(remitente);
            this.setPrecio(precio);
            this.agregarNuevoEstado( this.estadoInicial() );
    }
}