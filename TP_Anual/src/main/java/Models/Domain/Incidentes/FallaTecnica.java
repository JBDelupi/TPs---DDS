package Models.Domain.Incidentes;

import Models.Domain.Personas.Actores.Colaborador;

import java.util.List;

public class FallaTecnica extends Incidente {
    private Colaborador colaborador;
    private String foto;
    private String descripcion;
    private List<RegistroVisitaTecnica> visitasTecnicas;
    private TipoFallaTecnica tipo;
}
