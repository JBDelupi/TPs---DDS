package Models.Domain.Heladera.Incidentes.Utils;

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
