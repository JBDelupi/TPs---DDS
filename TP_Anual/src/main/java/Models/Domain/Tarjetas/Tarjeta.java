package Models.Domain.Tarjetas;

import Models.Domain.Personas.Actores.Persona;
import lombok.Getter;

import java.util.List;

@Getter
public abstract class Tarjeta {
    private String codigo;
    private Persona titular;
    private List<RegistroDeUso> usos;

    public void agregarNuevoUso(RegistroDeUso registroDeUso) {
        usos.add(registroDeUso);
    }
}

