package Models.Domain.Incidentes;

import Models.Domain.Personas.Actores.Tecnico;

import java.time.LocalDateTime;

public class RegistroVisitaTecnica {
    private Tecnico tecnico;
    private String descripcion;
    private LocalDateTime fecha;
    private String foto;
    private Boolean solucionado;
}
