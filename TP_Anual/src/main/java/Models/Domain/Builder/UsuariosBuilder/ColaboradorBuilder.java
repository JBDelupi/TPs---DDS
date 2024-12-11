package Models.Domain.Builder.UsuariosBuilder;

import Models.Domain.Personas.Actores.*;


public class ColaboradorBuilder {
    private Colaborador colaborador;

    public ColaboradorBuilder(){this.colaborador = new Colaborador(0.0);}

    public Colaborador construir(Persona persona) {
        if (persona instanceof Fisico) {

        }
        return this.colaborador;
    }
}
