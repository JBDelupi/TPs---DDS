package Models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
        Estado estadoInicial = new Estado(TipoEstado.PROCESANDO, sucursalOrigen);
        this.agregarNuevoEstado(estadoInicial);
        this.fueEntregado = false;
    }

    public void agregarNuevoEstado(Estado estado){
        estadoActual.add(estado);
    }

    public void paqueteEntregado() {
        Estado estadofinal = new Estado(TipoEstado.ENTREGADO, null);
        estadoActual.add(estadofinal);
        fueEntregado = true;
    }
}
