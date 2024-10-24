package Models.Domain.Tarjetas;


import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Vianda;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter

@Entity
@Table(name = "registro_de_uso")
public class RegistroDeUso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Temporal(TemporalType.DATE)
    private LocalDate fecha;

    @Transient
    private Heladera heladera;

    @Transient
    private Vianda vianda;

    @Enumerated(EnumType.STRING)
    private TipoAccion accion;

    public RegistroDeUso(Heladera heladera, Vianda vianda, TipoAccion accion){
        this.heladera = heladera;
        this.vianda = vianda;
        this.fecha = LocalDate.now();
        this.accion = accion;
    }


    public RegistroDeUso() {

    }
}
