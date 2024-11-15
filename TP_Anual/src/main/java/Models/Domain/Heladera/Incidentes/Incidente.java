package Models.Domain.Heladera.Incidentes;

import Models.Domain.Heladera.Heladera;

import Service.Notificacion.Mensaje.MensajeBienvenida;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;

@Getter
@Setter

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
@NoArgsConstructor

public abstract class Incidente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    protected LocalDateTime fecha;

    @ManyToOne()
    @JoinColumn(referencedColumnName = "id", name = "heladera_id")
    protected Heladera heladera;


    @Convert(converter = org.hibernate.type.TrueFalseConverter.class)
    @Column(name = "solucionado")
    protected Boolean solucionado;


}
