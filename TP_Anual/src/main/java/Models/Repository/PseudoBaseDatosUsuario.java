package Models.Repository;


import Controller.Actores.Rol;
import Controller.Actores.TipoRol;
import Models.Domain.Personas.Actores.Humano;
import Models.Domain.Personas.Actores.Juridico;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.Actores.Tecnico;
import Models.Domain.Personas.DatosPersonales.TipoDeDocumento;
import Models.Domain.Personas.Utilidades.TipoJuridico;
import Models.Domain.Personas.Utilidades.TipoRolNegocio;
import Service.Validador.CredencialDeAcceso;

import javax.swing.text.html.parser.Parser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.random.RandomGenerator;

public class PseudoBaseDatosUsuario {
    private static PseudoBaseDatosUsuario instacia = null;


    public List<Persona> base;

    public void agregar(Persona... p) {
        Collections.addAll(this.base, p);
    }

    private PseudoBaseDatosUsuario() {
        base = new ArrayList<>();

        // USUARIO HUMANO PARA TESTS
        Humano humano = new Humano();
        humano.setNombre("nombre");
        humano.setApellido("apellido");
        humano.setNumeroDocumento("123456789");
        humano.setTipoDeDocumento(TipoDeDocumento.DNI);

        CredencialDeAcceso credencialDeAcceso = new CredencialDeAcceso("h", "1");
        humano.setCredencialDeAcceso(credencialDeAcceso);
        humano.setId(RandomGenerator.getDefault().nextInt(0,100));
        humano.setRol(new Rol(TipoRol.HUMANO));
        agregar(humano);

        // USUARIO JURIDICO PARA TESTS
        Juridico juridico = new Juridico();
        juridico.setRazonSocial("razonSocial");
        juridico.setTipoJuridico(TipoJuridico.ONG);

        CredencialDeAcceso credencialDeAcceso2 = new CredencialDeAcceso("j", "1");
        juridico.setCredencialDeAcceso(credencialDeAcceso2);
        juridico.setId(RandomGenerator.getDefault().nextInt(0,100));
        juridico.setRol(new Rol(TipoRol.JURIDICO));
        agregar(juridico);
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

    public List<Tecnico> getTecnicos() {
        List<Tecnico> tecnicos = new ArrayList<>();
        for(Persona persona : base){
            if(persona.getRolNegocio().equals(TipoRolNegocio.TECNICO)){
                tecnicos.add((Tecnico) persona);
            }
        }
        return  tecnicos;
    }
}
