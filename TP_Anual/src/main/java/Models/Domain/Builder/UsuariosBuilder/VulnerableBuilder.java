package Models.Domain.Builder.UsuariosBuilder;

import Models.Domain.Personas.PersonaVulnerable;

public class VulnerableBuilder {
    private PersonaVulnerable personaVulnerable;

    public VulnerableBuilder() {this.personaVulnerable = new PersonaVulnerable();}

    public VulnerableBuilder nombre(String nombre) {
        this.personaVulnerable.setNombre(nombre);
        return this;
    }
    public VulnerableBuilder menoresACargo(Integer menoresACargo) {
        this.personaVulnerable.setMenoresACargo(menoresACargo);
        return this;
    }
    public PersonaVulnerable construir() {return this.personaVulnerable;}
}
