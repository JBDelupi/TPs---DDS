package Models.Domain.Heladera;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter @Getter

@Entity
@Table(name = "Vianda")
@NoArgsConstructor
public class Vianda {

    @Id
    @GeneratedValue()
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_de_caducidad")
    private LocalDate fechaDeCaducidad;

    @Column(name = "caloria")
    private Integer calorias;

    @Column(name = "peso")
    private Integer peso;


}
