package Models.Domain.Tarjetas;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Vianda;
import Models.Domain.Personas.Actores.Persona;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
public class TarjetaAccesosAHeladera extends Tarjeta{
    private List<SolicitudDeApertura> solicitudesDeApertura;

    public TarjetaAccesosAHeladera(Persona titular){
        super();
        this.titular = titular;
        this.solicitudesDeApertura = new ArrayList<>();

    }

    public void agregarNuevaSolicitud (SolicitudDeApertura unaSolicitud){
        solicitudesDeApertura.add(unaSolicitud);
    }
    public void agregarNuevoUso(Heladera heladera, TipoAccion quitar){

//        Vianda vianda = heladera.obtenerVianda();
        RegistroDeUso unNuevoUso = new RegistroDeUso(heladera,new Vianda(),TipoAccion.QUITAR);
        nuevoRegistro(unNuevoUso);
        System.out.println("Uso exitoso tarjeta");

    }

}
