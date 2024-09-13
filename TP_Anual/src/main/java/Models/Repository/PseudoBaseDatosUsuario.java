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
import Service.APIPuntos.AreaCobertura;
import Service.APIPuntos.Punto;
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

        // USUARIO TECNICO 1 PARA TEST
        Tecnico tecnico1 = new Tecnico();
        tecnico1.setNombre("Carlos");
        tecnico1.setApellido("Gomez");
        tecnico1.setTipoDocumento(TipoDeDocumento.DNI);
        tecnico1.setNroDocumento("123456789");
        tecnico1.setCuil("20123456789");

        // Creo puntos y áreas de cobertura para tecnico1
        Punto centro1 = new Punto();
        centro1.setLatitud("1");
        centro1.setLongitud("2");

        AreaCobertura areaCobertura1 = new AreaCobertura();
        areaCobertura1.setCentro(centro1);
        areaCobertura1.setRadio("15");

        // Asigno a tecnico1 el área
        tecnico1.setArea(areaCobertura1);
        CredencialDeAcceso credencialDeAcceso4 = new CredencialDeAcceso("tecnico1", "1");
        tecnico1.setCredencialDeAcceso(credencialDeAcceso4);
        tecnico1.setId(20);
       // tecnico1.setRol(new Rol(TipoRol.TECNICO));
        //agregar(tecnico1);

        // USUARIO TECNICO 2 PARA TEST
        Tecnico tecnico2 = new Tecnico();
        tecnico2.setNombre("Mariana");
        tecnico2.setApellido("Lopez");
        tecnico2.setTipoDocumento(TipoDeDocumento.DNI);
        tecnico2.setNroDocumento("987654321");
        tecnico2.setCuil("20987654321");

        // Creo puntos y áreas de cobertura para tecnico2
        Punto centro2 = new Punto();
        centro2.setLatitud("-2");
        centro2.setLongitud("-1");

        AreaCobertura areaCobertura2 = new AreaCobertura();
        areaCobertura2.setCentro(centro2);
        areaCobertura2.setRadio("20");

        // Asigno a tecnico2 el área
        tecnico2.setArea(areaCobertura2);

        //USUARIO ADMIN
        Humano humano11 = new Humano();
        humano11.setNombre("nombre");
        humano11.setApellido("apellido");
        humano11.setNumeroDocumento("123456789");
        humano11.setTipoDeDocumento(TipoDeDocumento.DNI);

        CredencialDeAcceso credencialDeAcceso3 = new CredencialDeAcceso("admin", "1");
        humano11.setCredencialDeAcceso(credencialDeAcceso3);
        humano11.setId(999);
        humano11.setRol(new Rol(TipoRol.ADMINISTRADOR));
        agregar(humano11);

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
