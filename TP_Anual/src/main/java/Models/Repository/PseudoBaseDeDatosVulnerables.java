package Models.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.OfrecerProducto;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.PersonaVulnerable;


public class PseudoBaseDeDatosVulnerables {
    private PersonaVulnerable vulnerable1;
    private PersonaVulnerable vulnerable2;
    private PersonaVulnerable vulnerable3;
    private PersonaVulnerable vulnerable4;
    private PersonaVulnerable vulnerable5;

    public List<PersonaVulnerable> baseVulnerable;
    private static PseudoBaseDeDatosVulnerables instacia = null;

    public void agregar(PersonaVulnerable... p) {
        Collections.addAll(this.baseVulnerable, p);
    }

    public  static PseudoBaseDeDatosVulnerables getInstance() {
        if(instacia==null){
            instacia = new PseudoBaseDeDatosVulnerables();
        }
        return instacia;
    }
    public PersonaVulnerable getId(String documento, ){
        return baseVulnerable.stream().filter(f->f.getTipoDeDocumento().equals(documento)).findFirst().get();
    }
}




