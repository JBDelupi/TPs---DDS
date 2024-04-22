package Models;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.util.List;

public class Encomienda extends Envio {
    private TipoEncomienda tipo;

    public Encomienda(TipoEncomienda tipo, Cliente destinatario, Cliente remitente, Double precio, CodigoRastreo codigoRastreo){
        this.tipo = tipo;
        this.setDestinatario(destinatario);
        this.setRemitente(remitente);
        this.setPrecio(precio);
        this.setCodigoRastreo(codigoRastreo);
    }
}
