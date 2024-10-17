package Models.Domain.Heladera.Incidentes;

import Models.Domain.Builder.IncidentesBuilder.VisitaTecnicaBuilder;
import Models.Domain.Heladera.EstadoHeladera;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Incidentes.Utils.RegistroVisitaTecnica;
import Models.Domain.Heladera.Incidentes.Utils.TipoFallaTecnica;
import Models.Domain.Heladera.Suscripciones.TipoDePublicacion;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.Actores.Tecnico;
import Service.Notificacion.Mensaje;
import Service.SistemaDeGeolocalizacion.SistemaGeolocalizacion;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class FallaTecnica extends Incidente {
    private Integer id;
    private Colaborador colaborador;
    private String foto;
    private String descripcion;
    private List<RegistroVisitaTecnica> visitasTecnicas;
    private TipoFallaTecnica tipo;
    private Boolean solucionado;
    private LocalDateTime fechaSolucionado;

    public FallaTecnica() {
        this.setSolucionado(false);
    }

    public Persona avisarATecnico(List<Persona> tecnicos) {
        SistemaGeolocalizacion sistemaGeolocalizacion = SistemaGeolocalizacion.getInstance();
        sistemaGeolocalizacion.setTecnicosRegistrados(tecnicos);
        Persona tecnicoMasCercano =  sistemaGeolocalizacion.masCercanoAPunto(heladera.getCoordenadas());
        Mensaje mensaje = this.generarMensaje(tecnicoMasCercano.getCorreElectronico(), "Falla tecnica","Falla Tecnica en" + this.heladera);
        tecnicoMasCercano.getMedioDeNotificacion().Notificar(mensaje);
        return tecnicoMasCercano;
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
