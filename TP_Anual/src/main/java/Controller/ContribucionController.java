package Controller;

import Controller.Actores.Rol;
import Models.Domain.FormasDeContribucion.*;
import Models.Domain.Heladera;
import Models.Domain.Personas.Colaborador;
import Models.Domain.Personas.Humano;
import Models.Domain.Personas.PersonaVulnerable;
import Models.Domain.Tarjeta.Tarjeta;
import Models.Domain.TipoDeOrganizacion;
import Models.Domain.TipoFrecuencia;
import Models.Domain.Vianda;


public class ContribucionController extends Controller {
    FormaDeContribucion nuevaDonacion;


    public ContribucionController(Colaborador colaborador){
        this.usuario = colaborador;
    }


    public void create(Object ... Args){
        nuevaDonacion = this.factoryMethod(Args);
        this.usuario.generarNuevaDonacion(nuevaDonacion);
    }

    // ------------------- LO HACEN TODOS ----------------------------------------//
    public FormaDeContribucion donacionDeDinero(Object ... Context){

        Double monto = (Double) Context[1];
        TipoFrecuencia tipoFrecuencia = (TipoFrecuencia) Context[2];

        FormaDeContribucion donacion = new DonacionDeDinero(monto,tipoFrecuencia);
        return  donacion;
    }


    public FormaDeContribucion donacionDeVianda(Object ... Context){

        this.checkUserRoleAndProceed(Rol.HUMANO);

        Vianda vianda = (Vianda) Context[1];
        Heladera heladera = (Heladera) Context[2];


        FormaDeContribucion donacion = new DonacionDeVianda(vianda,heladera);
        return donacion;

    }

    public FormaDeContribucion distribucionDeVianda(Object ... Context){

        this.checkUserRoleAndProceed(Rol.HUMANO);

        Heladera heladeraOrigen = (Heladera) Context[1];
        Heladera heladeraDestino = (Heladera) Context[2];
        Integer cantidadDeViandasAMover = (Integer) Context[3];
        String motivo = (String) Context[4];


        FormaDeContribucion donacion = new DistribucionDeViandas(heladeraOrigen, heladeraDestino, cantidadDeViandasAMover, motivo);
        return donacion;
    }

    public FormaDeContribucion registrarTarjeta(Object ... Context){

        this.checkUserRoleAndProceed(Rol.HUMANO);

        String nombre = (String) Context[1];
        Integer menoresACargo = (Integer) Context[2];
        PersonaVulnerable personaVulnerable = new PersonaVulnerable(nombre,menoresACargo );



        Tarjeta nuevaTarjeta = new Tarjeta( (Humano) usuario, personaVulnerable);
        FormaDeContribucion donacion = new EntregaDeTarjeta(nuevaTarjeta);


        return donacion;
    }





    public FormaDeContribucion hacerceCargoDeHeladera(Object ... Context){

        this.checkUserRoleAndProceed( Rol.JURIDICO );

        String nombreCaracteristico = (String) Context[1];
        TipoDeOrganizacion tipoDeOrganizacion = (TipoDeOrganizacion) Context[2];
        Heladera heladera = (Heladera) Context[3];

        FormaDeContribucion donacion = new HacerseCargoDeHeladera(nombreCaracteristico, tipoDeOrganizacion, heladera);
        return donacion;
    }




    public FormaDeContribucion factoryMethod(Object ... Context){
        FormaDeContribucion contribucion = null;
        switch( (TipoDonacion) Context[0] ){
            case DONACION_DINERO: contribucion = this.donacionDeDinero( Context ); break;
            case DONACION_DE_VIANDA: contribucion = this.donacionDeVianda( Context ); break;
            case HACERSE_CARGO_DE_HELADERA: contribucion = this.hacerceCargoDeHeladera( Context ); break;
            case DISTRIBUCION_VIANDAS: contribucion = this.distribucionDeVianda( Context ); break;
            case ENTREGA_TARJETAS : contribucion = this.registrarTarjeta( Context );
        }
        return contribucion;
    }


}
