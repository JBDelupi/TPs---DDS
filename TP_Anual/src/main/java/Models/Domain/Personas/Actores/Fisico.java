package Models.Domain.Personas.Actores;

import Models.Domain.Personas.DatosPersonales.TipoDeDocumento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter

@Entity
@DiscriminatorValue("Fisico")
public class Fisico extends Persona {

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento")
    private TipoDeDocumento tipoDeDocumento;

    @Column(name = "numero_Documento")
    private String numeroDocumento;



}