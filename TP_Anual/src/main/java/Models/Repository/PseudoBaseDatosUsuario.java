package Models.Repository;


import Models.Domain.Personas.Actores.Humano;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.Utilidades.TipoRolNegocio;

import javax.swing.text.html.parser.Parser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PseudoBaseDatosUsuario {
    private static PseudoBaseDatosUsuario instacia = null;


    public List<Persona> base;

    public void agregar(Persona... p) {
        Collections.addAll(this.base, p);
    }

    private PseudoBaseDatosUsuario() {
        base = new ArrayList<>();
    }

    public static PseudoBaseDatosUsuario getInstance() {
        if(instacia==null){
            instacia = new PseudoBaseDatosUsuario();
        }
        return instacia;
    }

    public Persona getId(String id){
        return base.stream().filter(f->f.getId() == Integer.parseInt(id) ).findFirst().get();
    }

    public Persona searchUser(String user) {
        return base.stream()
                .filter(f -> f.getCredencialDeAcceso().getNombreUsuario().equals(user))
                .findAny()
                .orElse(null);
    }

    public Humano searchUserTarjeta(String user) {
        List<Humano> humanos = new ArrayList<>();
        for(Persona persona : base){
            if(persona.getRolNegocio().equals(TipoRolNegocio.HUMANO)){
                humanos.add((Humano) persona);
            }
        }
      return   humanos.stream().filter(f->f.getTarjeta().getCodigo().equals(user)).findAny().orElse(null);
    }

}
