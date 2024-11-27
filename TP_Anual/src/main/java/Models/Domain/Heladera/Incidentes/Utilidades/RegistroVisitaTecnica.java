package Models.Domain.Heladera.Incidentes.Utilidades;

import Models.Domain.Personas.Actores.Tecnico;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter

@Entity
@Table(name = "registro_visita_tecnica")
public class RegistroVisitaTecnica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @ManyToOne()
    @JoinColumn(referencedColumnName = "id", name = "tecnico_visita_id")
    private Tecnico tecnico;

    @Column(name = "descripcion")
    private String descripcion;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fecha;

    @Transient
    private String foto;
    @Convert(converter = org.hibernate.type.TrueFalseConverter.class)
    private Boolean visitaExitosa;




}
