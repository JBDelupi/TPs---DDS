package Models.Domain.FormasDeContribucion.Utilidades;

import Models.Repository.RepoContribucion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter

@Entity
@Table(name = "Contribucion")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo")

public abstract class Contribucion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Transient
    public String nombre;


    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_de_donacion")
    protected LocalDate fechaDeDonacion = LocalDate.now();

    public Double generarPuntaje(){
        return 0.0;
    }

    public abstract String getDetalle();


}

