package Models.Domain.Tarjetas;

import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Persona;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter

@Entity
@Table(name = "Tarjeta")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Tarjeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer codigo;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    protected Persona titular;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tarjeta_id")
    protected List<RegistroDeUso> usos;

    public Tarjeta() {
        this.usos = new ArrayList<>();
    }

    public void nuevoRegistro(RegistroDeUso registroDeUso) {
        usos.add(registroDeUso);
    }
}

