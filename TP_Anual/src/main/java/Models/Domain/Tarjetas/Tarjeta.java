package Models.Domain.Tarjetas;

import Models.Domain.Personas.Actores.Persona;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public abstract class Tarjeta {
    protected String codigo;
    protected Persona titular;
    protected List<RegistroDeUso> usos;

    public Tarjeta() {

    }

    public void agregarNuevoUso(RegistroDeUso registroDeUso) {
        usos.add(registroDeUso);
    }
}

