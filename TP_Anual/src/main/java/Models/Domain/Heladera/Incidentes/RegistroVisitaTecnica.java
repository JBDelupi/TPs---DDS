package Models.Domain.Heladera.Incidentes;

import Models.Domain.Heladera.EstadoHeladera;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Tecnico;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class RegistroVisitaTecnica {
    private Tecnico tecnico;
    private String descripcion;
    private LocalDateTime fecha;
    private String foto;
    private Boolean visitaExitosa; //Se soluciono el problema

    public RegistroVisitaTecnica(Tecnico tecnico, LocalDateTime fecha, Boolean solucionado) {
        this.tecnico = tecnico;
        this.fecha = fecha;
        this.foto = "";
        this.visitaExitosa = solucionado;
    }

}
