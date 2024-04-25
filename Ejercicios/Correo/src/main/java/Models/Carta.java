package Models;

import java.util.List;

public class Carta extends Envio{
    private TipoCarta tipo;
    private Boolean selloRojo;

    public Carta(Boolean selloRojo,TipoCarta tipo, Cliente destinatario, Cliente remitente, Double precio, CodigoRastreo codigoRastreo){
        this.selloRojo = selloRojo;
        this.tipo = tipo;
        this.setDestinatario(destinatario);
        this.setRemitente(remitente);
        this.setPrecio(precio);
        this.setCodigoRastreo(codigoRastreo);
    }
}
