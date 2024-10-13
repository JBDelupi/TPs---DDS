package Models.Domain.Heladera;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter @Getter
public class Vianda {
    private Integer id;
    private String nombre;
    private LocalDate fechaDeCaducidad;
    private Integer calorias;
    private Integer peso;
}
