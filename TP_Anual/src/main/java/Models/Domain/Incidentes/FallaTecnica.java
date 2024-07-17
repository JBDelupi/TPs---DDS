package Models.Domain.Incidentes;

import Models.Domain.Heladera.EstadoHeladera;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Colaborador;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class FallaTecnica extends Incidente {
    private Colaborador colaborador;
    private String foto;
    private String descripcion;
    private List<RegistroVisitaTecnica> visitasTecnicas;
    private TipoFallaTecnica tipo;


    public FallaTecnica(Heladera heladera, Colaborador colaborador) {
        this.heladera = heladera;
        this.heladera.setActual(EstadoHeladera.NO_DISPONIBLE);
        this.foto = "";
        this.colaborador = colaborador;
        this.descripcion = "";
        this.visitasTecnicas = new ArrayList<>();
        this.tipo = null;
    }
}
