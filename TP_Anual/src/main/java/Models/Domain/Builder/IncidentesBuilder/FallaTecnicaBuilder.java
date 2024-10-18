package Models.Domain.Builder.IncidentesBuilder;

import Models.Domain.Heladera.EstadoHeladera;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Incidentes.FallaTecnica;
import Models.Domain.Heladera.Incidentes.Utils.RegistroVisitaTecnica;
import Models.Domain.Heladera.Suscripciones.TipoDePublicacion;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.Actores.TipoRol;

import java.time.LocalDateTime;
import java.util.List;

public class FallaTecnicaBuilder {
    private FallaTecnica fallaTecnica;

    public FallaTecnicaBuilder() { fallaTecnica = new FallaTecnica(); }

    public FallaTecnicaBuilder heladera(Heladera heladera) {
        fallaTecnica.setHeladera(heladera);
        heladera.setEstadoActual(EstadoHeladera.NO_DISPONIBLE);
        heladera.generarNuevaPublicacion(TipoDePublicacion.SUFRIO_DESPERFECTO);
        fallaTecnica.avisarATecnico();

        return this;
    }

    public FallaTecnicaBuilder colaborador(Persona colaborador){
        fallaTecnica.setColaborador((Colaborador)colaborador.getRol(TipoRol.COLABORADOR));
        return this;
    }

    public FallaTecnicaBuilder foto(String foto){
        fallaTecnica.setFoto(foto);
        return this;
    }

    public FallaTecnicaBuilder descripcion(String descripcion){
        fallaTecnica.setDescripcion(descripcion);
        return this;
    }


    public FallaTecnicaBuilder visitasTecnicas (List<RegistroVisitaTecnica> visitasTecnicas){
        fallaTecnica.setVisitasTecnicas(visitasTecnicas);
        return this;
    }

    public FallaTecnicaBuilder solucionado(Boolean solucionado){
        fallaTecnica.setSolucionado(solucionado);
        return this;
    }

    public FallaTecnicaBuilder fecha(LocalDateTime fecha){
        fallaTecnica.setFecha(fecha);
        return this;
    }

    public FallaTecnica construir() {
        return this.fallaTecnica;
    }
}
