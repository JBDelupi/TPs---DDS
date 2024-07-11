package Models.Domain.Builder;

import Models.Domain.Personas.Humano;
import Models.Domain.Personas.PersonaVulnerable;
import Models.Domain.Tarjeta.Tarjeta;

public class TarjetaBuilder {
    private Tarjeta tarjeta;

    public TarjetaBuilder() {this.tarjeta = new Tarjeta();}

    public TarjetaBuilder titular(PersonaVulnerable titular) {
        this.tarjeta.setTitular(titular);
        return this;
    }

    public TarjetaBuilder colaborador(Humano colaborador) {
        this.tarjeta.setColaborador(colaborador);
        return this;
    }

    public Tarjeta construir(){return this.tarjeta;}
}
