package Service.DeccoSaludAPI.DTO.Reporte;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter

@Entity
@Table(name = "informacion")
public class Informacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "barrio")
    private String barrio;
    private int cantidadPersonas;
    @ElementCollection()
    private List<String> personas;
}
