package Models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public abstract class Envio {
    private Cliente destinatario;
    private Cliente remitente;
    private Double precio;
    private CodigoRastreo codigoRastreo;
    private List<Estado> estadoActual;
    private Boolean fueEntregado;
    private Sucursal sucursalOrigen;

    public Envio(){
        
        this.estadoActual = new ArrayList<>();
        this.fueEntregado = false;
        this.codigoRastreo = new CodigoRastreo( Double.toString( new Random().nextDouble() ) );

    }

    public Estado estadoInicial(){
        return  new Estado(TipoEstado.PROCESANDO, new Sucursal(0, "REMITENTE", remitente.getDireccion()) );
    }

    public void agregarNuevoEstado(Estado estado){
        estadoActual.add(estado);
    }

    public void paqueteEntregado() {
        Estado estadofinal = new Estado(TipoEstado.ENTREGADO, new Sucursal(1,"ENTREGADO",destinatario.getDireccion()) );
        estadoActual.add(estadofinal);
        fueEntregado = true;
    }


}
