package Models.Domain.Reporte;

import Service.TareaDiferida.ChromeTask;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "reporte")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class TemplateReporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Temporal(TemporalType.DATE)
    private LocalDate fecha;

    @ElementCollection
    @CollectionTable(
            name = "reporte_items",      // Nombre de la tabla que almacenará los elementos de la colección
            joinColumns = @JoinColumn(name = "reporte_id")  // Relación con la tabla principal
    )
    private List<List<String>> items;

    public TemplateReporte() {
        fecha = LocalDate.now();
        items = new ArrayList<>();
    }

    public abstract  void obtenerListado();
}
