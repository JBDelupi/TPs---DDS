package Models.Domain.Personas.Actores;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "Rol")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    protected TipoRol tipo;

    public abstract List<String> getPermisos(Persona persona);


}