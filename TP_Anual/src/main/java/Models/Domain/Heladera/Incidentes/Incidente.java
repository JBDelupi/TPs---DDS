package Models.Domain.Heladera.Incidentes;

import Models.Domain.Heladera.Heladera;

import Service.Notificacion.Mensaje.MensajeBienvenida;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Transient // TIEMPO
    protected LocalDateTime fecha;

    @Transient
    protected Heladera heladera;

    @Column(name = "solucionado")
    protected Boolean solucionado;

}
