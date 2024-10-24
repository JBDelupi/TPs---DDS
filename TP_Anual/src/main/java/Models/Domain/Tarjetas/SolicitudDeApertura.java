package Models.Domain.Tarjetas;

import Models.Domain.FormasDeContribucion.Utilidades.TipoDonacion;
import Models.Domain.Heladera.Heladera;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter @Setter
@Entity
@Table(name = "SolicitudDeApertura")
@NoArgsConstructor
public class SolicitudDeApertura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSolicitudApertura;

    @Enumerated(EnumType.STRING)
    private TipoDonacion accion;

    @Transient // TIEMPO
    private LocalDateTime fechaApertura;

    @Transient
    private Heladera heladera;

    @Transient
    private LocalDateTime fechaLimite;

    @Column(name = "hora_limite")
    private Double horaLimite;

    @Convert(converter = org.hibernate.type.TrueFalseConverter.class)
    private Boolean realizada;

    public SolicitudDeApertura(TipoDonacion accion, Heladera heladera) {
        fechaApertura = LocalDateTime.now();
        realizada = Boolean.FALSE;
        this.accion = accion;
        this.horaLimite = 1.00;
        fechaLimite = fechaApertura.plusMinutes((long)(horaLimite*60));
    }
}
