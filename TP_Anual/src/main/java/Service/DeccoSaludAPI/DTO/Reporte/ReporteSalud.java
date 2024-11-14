package Service.DeccoSaludAPI.DTO.Reporte;

import Service.DeccoSaludAPI.DTO.RespuestaDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Reporte_Decco_Salud")
public class ReporteSalud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private LocalDate fecha;

    @OneToMany()
    @JoinColumn(referencedColumnName = "reporte_id")
    List<Informacion> respuestas;

    public ReporteSalud() {
        respuestas = new ArrayList<>();
    }

    public void agregarInformacion(Informacion informacion) {
        respuestas.add(informacion);
    }

}
