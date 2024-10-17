package Models.Domain.Builder.UsuariosBuilder;

import Models.Domain.Personas.Actores.PersonaVulnerable;
import Models.Domain.Personas.Actores.TipoRol;

import java.time.LocalDate;

public class VulnerableBuilder {
    private PersonaVulnerable personaVulnerable;

    public VulnerableBuilder() {this.personaVulnerable = new PersonaVulnerable();}

    public VulnerableBuilder menoresACargo(Integer menoresACargo) {
        this.personaVulnerable.setMenoresACargo(menoresACargo);
        return this;
    }

    public VulnerableBuilder flagSituacionDeCalle(Boolean flag) {
        this.personaVulnerable.setFlagSituacionDeCalle(flag);
        return this;
    }

    public VulnerableBuilder fechaRegistro(LocalDate fechaRegistro){
        this.personaVulnerable.setFechaRegistro(fechaRegistro);
        return this;
    }

    public PersonaVulnerable construir() {

        return this.personaVulnerable;}
}
