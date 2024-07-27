package Models.Domain.Heladera.Incidentes;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Incidentes.Utils.TipoAlerta;
import Models.Domain.Personas.Actores.Tecnico;
import Service.Notificacion.Mensaje;
import Service.SistemaDeGeolocalizacion.SistemaGeolocalizacion;
import lombok.Getter;

import java.util.List;

@Getter
public class Alerta extends Incidente {
    private TipoAlerta tipo;

    public Alerta(TipoAlerta tipo, Heladera heladera) {
        this.tipo = tipo;
        this.heladera = heladera;
    }

    public Tecnico avisarATecnico(List<Tecnico> tecnicos) {
        SistemaGeolocalizacion sistemaGeolocalizacion = SistemaGeolocalizacion.getInstance();
        sistemaGeolocalizacion.setTecnicosRegistrados(tecnicos);
        Tecnico tecnicoMasCercano = sistemaGeolocalizacion.masCercanoAPunto(heladera.getCoordenadas());
        Mensaje mensaje = this.generarMensaje(tecnicoMasCercano.getCodigoDeNotificacion(), tipo.name(),tipo.name() + this.heladera);
        tecnicoMasCercano.getMedioDeNotificacion().Notificar(mensaje);
        return tecnicoMasCercano;
    }

}
