package Models.Domain.Heladera.Incidentes;

import Models.Domain.Builder.IncidentesBuilder.VisitaTecnicaBuilder;
import Models.Domain.Heladera.EstadoHeladera;
import Models.Domain.Heladera.Incidentes.Utils.RegistroVisitaTecnica;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Persona;
import Service.Notificacion.Mensaje.MensajeTecnico;
import Service.SistemaDeGeolocalizacion.SistemaGeolocalizacion;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class FallaTecnica extends Incidente {
    private Integer id;
    private Colaborador colaborador;
    private String foto;
    private String descripcion;
    private List<RegistroVisitaTecnica> visitasTecnicas;
    private Boolean solucionado;
    private LocalDateTime fechaSolucionado;

    public FallaTecnica() {
        this.setSolucionado(false);

    }

    public void avisarATecnico() {
        Persona tecnicoMasCercano =  SistemaGeolocalizacion.getInstance().masCercanoAPunto(heladera.getCoordenadas());
        tecnicoMasCercano.getMedioDeNotificacion().Notificar(new MensajeTecnico(tecnicoMasCercano.getCodigoDeNotificacion(), "Falla Tecnica en la localidad de: " + this.heladera.getDireccion().getLocalidad() + ".Direccion: " + this.heladera.getDireccion().getCalle() + this.heladera.getDireccion().getCalle()));
    }

    public void crearRegistroDeVisita(Persona tecnico, LocalDateTime fecha, Boolean solucionado) {
        VisitaTecnicaBuilder visitaTecnicaBuilder = new VisitaTecnicaBuilder();
        RegistroVisitaTecnica registroVisita = visitaTecnicaBuilder
                .tecnico(tecnico)
                .fecha(fecha)
                .visitaExitosa(solucionado)
                .construir();
        visitasTecnicas.add(registroVisita);
        if (registroVisita.getVisitaExitosa()) {
            this.solucionado = true;
            heladera.setEstadoActual(EstadoHeladera.DISPONIBLE);
        }
    }
}
