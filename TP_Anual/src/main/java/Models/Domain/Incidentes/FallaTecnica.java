package Models.Domain.Incidentes;

import Models.Domain.Heladera.EstadoHeladera;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Tecnico;
import Service.SistemaDeGeolocalizacion.SistemaGeolocalizacion;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FallaTecnica extends Incidente {
    private Colaborador colaborador;
    private String foto;
    private String descripcion;
    private List<RegistroVisitaTecnica> visitasTecnicas;
    private TipoFallaTecnica tipo;
    private Boolean solucionado;

    public FallaTecnica(Heladera heladera, Colaborador colaborador) {
        this.heladera = heladera;
        this.heladera.setEstadoActual(EstadoHeladera.NO_DISPONIBLE);
        //this.avisarATecnico();
        this.foto = "";
        this.colaborador = colaborador;
        this.descripcion = "";
        this.visitasTecnicas = new ArrayList<>();
        this.tipo = null;
    }

    public List<Tecnico> avisarATecnico(List<Tecnico> tecnicos) {
        SistemaGeolocalizacion sistemaGeolocalizacion = new SistemaGeolocalizacion();
        sistemaGeolocalizacion.setTecnicosRegistrados(tecnicos);
        return sistemaGeolocalizacion.getTecnicosRegistrados().stream().
                filter(f-> sistemaGeolocalizacion.estaDentroDe(f.getArea(), heladera.getCoordenadas()))
                .collect(Collectors.toList());
    }

    public void crearRegistroDeVisita(Tecnico tecnico, LocalDateTime fecha, Boolean solucionado){
        RegistroVisitaTecnica registroVisita = new RegistroVisitaTecnica(tecnico, fecha, solucionado);
        visitasTecnicas.add(registroVisita);
        if (registroVisita.getVisitaExitosa()){
            this.solucionado = true;
            heladera.setEstadoActual(EstadoHeladera.DISPONIBLE);
        }
    }
}
