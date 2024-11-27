package Models.Domain.Tarjetas;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Tarjetas.Trazabilidad.RegistroDeUso;
import Models.Domain.Tarjetas.Trazabilidad.SolicitudDeApertura;
import Models.Domain.Tarjetas.Utilidades.TipoAccion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Getter


@Entity
@DiscriminatorValue("tarjeta_accesos")
@NoArgsConstructor

public class TarjetaAccesos extends Tarjeta{

    @OneToMany()
    @JoinColumn(name = "tarjeta_id")
    private List<SolicitudDeApertura> solicitudesDeApertura;

    public TarjetaAccesos(Persona titular){
        super();
        this.titular = titular;
        this.solicitudesDeApertura = new ArrayList<>();

    }


    public void agregarNuevaSolicitud (SolicitudDeApertura unaSolicitud){
        solicitudesDeApertura.add(unaSolicitud);
    }

    public void agregarNuevoUso(Heladera heladera, TipoAccion accion){
        RegistroDeUso unNuevoUso = new RegistroDeUso(heladera,heladera.obtenerVianda(),accion);
        nuevoRegistro(unNuevoUso);
    }

}
