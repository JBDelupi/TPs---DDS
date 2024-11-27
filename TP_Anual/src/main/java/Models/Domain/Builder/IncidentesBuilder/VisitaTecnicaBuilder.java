package Models.Domain.Builder.IncidentesBuilder;

import Models.Domain.Heladera.Incidentes.Utilidades.RegistroVisitaTecnica;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.Actores.Tecnico;
import Models.Domain.Personas.Actores.TipoRol;

import java.time.LocalDateTime;

public class VisitaTecnicaBuilder {
    private RegistroVisitaTecnica registroVisitaTecnica;

    public VisitaTecnicaBuilder() {registroVisitaTecnica = new RegistroVisitaTecnica();}

    public VisitaTecnicaBuilder tecnico(Persona tecnico){
        registroVisitaTecnica.setTecnico((Tecnico)tecnico.getRol(TipoRol.TECNICO));
        return this;
    }

    public VisitaTecnicaBuilder descripcion(String descripcion){
        registroVisitaTecnica.setDescripcion(descripcion);
        return this;
    }

    public VisitaTecnicaBuilder fecha(LocalDateTime fecha){
        registroVisitaTecnica.setFecha(fecha);
        return this;
    }

    public VisitaTecnicaBuilder foto(String foto){
        registroVisitaTecnica.setFoto(foto);
        return this;
    }

    public VisitaTecnicaBuilder visitaExitosa(Boolean visitaExitosa){
        registroVisitaTecnica.setVisitaExitosa(visitaExitosa);
        return this;
    }

    public RegistroVisitaTecnica construir(){return this.registroVisitaTecnica;}
}
