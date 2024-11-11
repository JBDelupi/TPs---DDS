package Models.Domain.Heladera.Incidentes;

import Models.Domain.Builder.IncidentesBuilder.VisitaTecnicaBuilder;
import Models.Domain.Heladera.EstadoHeladera;
import Models.Domain.Heladera.Incidentes.Utils.RegistroVisitaTecnica;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Persona;
import Service.Notificacion.Mensaje.MensajeTecnico;
import Service.SistemaDeGeolocalizacion.SistemaGeolocalizacion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter

@Entity
@DiscriminatorValue("falla_tecnica")
public class FallaTecnica extends Incidente {

    @ManyToOne()
    @JoinColumn(referencedColumnName = "id", name = "colaborador_id")
    private Colaborador colaborador;

    @Column(name = "foto")
    private String foto;

    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_persona")
    private List<RegistroVisitaTecnica> visitasTecnicas;


    @Transient
    private LocalDateTime fechaSolucionado;

    public FallaTecnica() {
        this.setSolucionado(false);
        this.visitasTecnicas = new ArrayList<>();
    }

    public void avisarATecnico() {
        Persona tecnicoMasCercano =  SistemaGeolocalizacion.getInstance().masCercanoAPunto(heladera.getDireccion().getCentro());
        if(tecnicoMasCercano != null){
            tecnicoMasCercano.getMedioDeNotificacion().Notificar(new MensajeTecnico(tecnicoMasCercano.getCodigoDeNotificacion(), "Falla Tecnica en la localidad de: " + this.heladera.getDireccion().getLocalidad() + ".Direccion: " + this.heladera.getDireccion().getCalle() + this.heladera.getDireccion().getCalle()));
        }

    }

    public void crearRegistroDeVisita(Persona tecnico, String descripcion, Boolean solucionado, String imagen) {
        VisitaTecnicaBuilder visitaTecnicaBuilder = new VisitaTecnicaBuilder();

        RegistroVisitaTecnica registroVisita = visitaTecnicaBuilder
                .descripcion(descripcion)
                .tecnico(tecnico)
                .fecha(LocalDateTime.now())
                .visitaExitosa(solucionado)
                .construir();
        visitasTecnicas.add(registroVisita);

        if (imagen !=null) {
            registroVisita.setFoto(imagen);
        }
        if (solucionado) {
            this.solucionado = true;
            heladera.setEstadoActual(EstadoHeladera.DISPONIBLE);
        }

    }

}
