package Models.Repository;


import Controller.Actores.RolUsuario;
import Models.Domain.Builder.CredencialDeAccesoBuilder;
import Models.Domain.Builder.UsuariosBuilder.ColaboradorBuilder;
import Models.Domain.Builder.UsuariosBuilder.TecnicoBuilder;
import Models.Domain.Builder.UsuariosBuilder.VulnerableBuilder;
import Models.Domain.Personas.Actores.*;
import Models.Domain.Personas.DatosPersonales.TipoDeDocumento;
import Models.Domain.Personas.Utilidades.TipoJuridico;
import Models.Domain.Personas.Utilidades.TipoRolNegocio;
import Models.Domain.Tarjetas.Tarjeta;
import Models.Domain.Tarjetas.TarjetaAlimentar;
import Models.Domain.Tarjetas.TipoAccion;
import Service.APIPuntos.AreaCobertura;
import Service.APIPuntos.Punto;
import Service.Validador.CredencialDeAcceso;

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

        // USUARIO FISICO COLABORADOR PARA TESTS
        Fisico fisico = new Fisico();
        fisico.setNombre("Pedro");
        fisico.setApellido("Garcia");
        fisico.setNumeroDocumento("847872313");
        fisico.setTipoDeDocumento(TipoDeDocumento.DNI);
        fisico.setTipoUsuario(RolUsuario.FISICO);
        fisico.setCorreElectronico("jgarcia@frba.utn.edu.ar");

        CredencialDeAccesoBuilder credencialDeAccesoBuilder = new CredencialDeAccesoBuilder();
        CredencialDeAcceso credencialDeAcceso = credencialDeAccesoBuilder
                .nombreUsuario("f")
                .contrasenia("f")
                .construir();

        fisico.setCredencialDeAcceso(credencialDeAcceso);
        fisico.setId(1);
        ColaboradorBuilder colaboradorBuilder = new ColaboradorBuilder();
        Colaborador colaborador = colaboradorBuilder
                .construir(fisico);
        colaborador.setPuntaje(1000000.00);
        fisico.agregarRol(colaborador);
        agregar(fisico);

        // USUARIO JURIDICO COLABORADOR PARA TESTS
        Juridico juridico = new Juridico();
        juridico.setRazonSocial("unaBuenaRazon");
        juridico.setTipoJuridico(TipoJuridico.ONG);
        juridico.setCorreElectronico("mailpiola@gmail.com");
        juridico.setTipoUsuario(RolUsuario.JURIDICO);

        CredencialDeAccesoBuilder credencialDeAccesoBuilder2 = new CredencialDeAccesoBuilder();
        CredencialDeAcceso credencialDeAcceso2 = credencialDeAccesoBuilder2
                .nombreUsuario("j")
                .contrasenia("j")
                .construir();

        juridico.setCredencialDeAcceso(credencialDeAcceso);
        juridico.setId(2);
        ColaboradorBuilder colaboradorBuilder2 = new ColaboradorBuilder();
        Colaborador colaborador2 = colaboradorBuilder2
                .construir(juridico);
        juridico.agregarRol(colaborador2);
        agregar(juridico);

        // USUARIO FISICO TECNICO 1 PARA TEST

        Fisico fisicoT = new Fisico();
        fisicoT.setNombre("Pedro");
        fisicoT.setApellido("Garcia");
        fisicoT.setNumeroDocumento("54631223");
        fisicoT.setTipoDeDocumento(TipoDeDocumento.DNI);
        fisicoT.setTipoUsuario(RolUsuario.FISICO);
        fisicoT.setCorreElectronico("quemailmalo@gmail.com");

        CredencialDeAccesoBuilder credencialDeAccesoBuilder3 = new CredencialDeAccesoBuilder();
        CredencialDeAcceso credencialDeAcceso3 = credencialDeAccesoBuilder3
                .nombreUsuario("t")
                .contrasenia("t")
                .construir();

        fisicoT.setCredencialDeAcceso(credencialDeAcceso3);
        fisicoT.setId(3);

        TecnicoBuilder tecnicoBuilder = new TecnicoBuilder();

        // Creo puntos y áreas de cobertura para tecnico1
        Punto centro1 = new Punto();
        centro1.setLatitud("1");
        centro1.setLongitud("2");
        AreaCobertura areaCobertura1 = new AreaCobertura();
        areaCobertura1.setCentro(centro1);
        areaCobertura1.setRadio("15");

        Tecnico tecnico1 = tecnicoBuilder
                .area(areaCobertura1)
                .cuil("8478123")
                .construir();
        tecnico1.setTipo(TipoRol.TECNICO);

        fisicoT.agregarRol(tecnico1);

        agregar(fisicoT);


        //USUARIO ADMIN
        Fisico fisico11 = new Fisico();
        fisico11.setNombre("Lucas");
        fisico11.setApellido("Garcia");
        fisico11.setNumeroDocumento("6456234235");
        fisico11.setTipoDeDocumento(TipoDeDocumento.DNI);

        CredencialDeAccesoBuilder credencialDeAccesoBuilder4 = new CredencialDeAccesoBuilder();
        CredencialDeAcceso credencialDeAcceso4 = credencialDeAccesoBuilder4
                .nombreUsuario("a")
                .contrasenia("a")
                .construir();

        fisico11.setCredencialDeAcceso(credencialDeAcceso4);
        fisico11.setId(4);

        fisico11.setTipoUsuario(RolUsuario.ADMINISTRADOR);
        agregar(fisico11);


        // USUARIO FISICO VULNERABLE PARA TESTS
        Fisico fisicoV = new Fisico();
        fisicoV.setNombre("Bauti");
        fisicoV.setApellido("De las Cuevas");
        fisicoV.setNumeroDocumento("847872313");
        fisicoV.setTipoDeDocumento(TipoDeDocumento.DNI);
        fisicoV.setTipoUsuario(RolUsuario.FISICO);
        fisicoV.setCorreElectronico("unbuenmail@gmail.com");

        CredencialDeAccesoBuilder credencialDeAccesoBuilder5 = new CredencialDeAccesoBuilder();
        CredencialDeAcceso credencialDeAcceso5 = credencialDeAccesoBuilder5
                .nombreUsuario("v")
                .contrasenia("v")
                .construir();

        fisicoV.setCredencialDeAcceso(credencialDeAcceso5);
        fisicoV.setId(5);
        VulnerableBuilder vulnerableBuilder = new VulnerableBuilder();
        PersonaVulnerable personaVulnerable = vulnerableBuilder
                .menoresACargo(4)
                .flagSituacionDeCalle(true)
                .construir();
        fisicoV.agregarRol(personaVulnerable);
        TarjetaAlimentar tarjeta = new TarjetaAlimentar(fisicoV);

        tarjeta.agregarNuevoUso(PseudoBaseDatosHeladera.getInstance().getId("1"), TipoAccion.QUITAR );

        agregar(fisicoV);

        // USUARIO FISICO VULNERABLE 2 PARA TESTS
        Fisico fisicoV2 = new Fisico();
        fisicoV2.setNombre("Pedro");
        fisicoV2.setApellido("Alvarez");
        fisicoV2.setNumeroDocumento("4562525");
        fisicoV2.setTipoDeDocumento(TipoDeDocumento.DNI);
        fisicoV2.setTipoUsuario(RolUsuario.FISICO);
        fisicoV2.setCorreElectronico("unbuedwqfgw@gmail.com");

        CredencialDeAccesoBuilder credencialDeAccesoBuilder6 = new CredencialDeAccesoBuilder();
        CredencialDeAcceso credencialDeAcceso6 = credencialDeAccesoBuilder6
                .nombreUsuario("v2")
                .contrasenia("v2")
                .construir();

        fisicoV2.setCredencialDeAcceso(credencialDeAcceso6);
        fisicoV2.setId(6);
        VulnerableBuilder vulnerableBuilder2 = new VulnerableBuilder();
        PersonaVulnerable personaVulnerable2 = vulnerableBuilder2
                .menoresACargo(4)
                .flagSituacionDeCalle(true)
                .construir();
        fisicoV2.agregarRol(personaVulnerable2);
        TarjetaAlimentar tarjeta2 = new TarjetaAlimentar(fisicoV2);
        tarjeta2.setCantMaxUso(20);
        tarjeta2.agregarNuevoUso(PseudoBaseDatosHeladera.getInstance().getId("2"), TipoAccion.QUITAR );

        agregar(fisicoV2);

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

    public Fisico searchUserTarjeta(String tarjeta) {
        List<Fisico> fisicos = new ArrayList<>();
        for(Persona persona : base){
            if(persona.checkRol(TipoRol.COLABORADOR) && persona instanceof Fisico){
                fisicos.add((Fisico) persona);
            }
        }
        return   fisicos.stream().filter(f-> ((Colaborador)f.getRol(TipoRol.COLABORADOR)).getTarjeta().getCodigo().equals(tarjeta)).findAny().orElse(null);
    }

    public List<Persona> getTecnicos() {
        List<Persona> tecnicos = new ArrayList<>();
        for(Persona persona : base){
            if(persona.checkRol(TipoRol.TECNICO)){
                tecnicos.add(persona);
            }
        }
        return  tecnicos;
    }

    public List<Persona> getColaboradores() {
        List<Persona> colaboradores = new ArrayList<>();
        for(Persona persona : base){
            if(persona.checkRol(TipoRol.COLABORADOR)){
                colaboradores.add(persona);
            }
        }
        return colaboradores;
    }

    public List<Fisico> getColaboradoresYFisicos() {
        List<Fisico> colaboradores = new ArrayList<>();
        for(Persona persona : base){
            if(persona.checkRol(TipoRol.COLABORADOR) && persona instanceof Fisico ){
                colaboradores.add( (Fisico) persona);
            }
        }
        return colaboradores;
    }



    public List<Persona> getPersonasVulnerables() {
        List<Persona> personaVulnerables = new ArrayList<>();
        for(Persona persona : base){
            if(persona.checkRol(TipoRol.VULNERABLE)){
                personaVulnerables.add(persona);
            }
        }
        return  personaVulnerables;
    }


    public List<Tarjeta> getTarjetasAlimentar() {
        List<Tarjeta> tarjetas = new ArrayList<>();
        for(Persona persona : base){
            if(persona.checkRol(TipoRol.VULNERABLE)){
                PersonaVulnerable personaVulnerable = (PersonaVulnerable) persona.getRol(TipoRol.VULNERABLE);
                tarjetas.add(personaVulnerable.getTarjeta());
                System.out.println("Vulnerable agregado a la lista de DeccoSalud API: " + persona.getId());
            }
        }
        return  tarjetas;
    }


}
