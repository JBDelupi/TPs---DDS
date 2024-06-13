package Controller;

import Controller.Actores.Rol;
import Models.Domain.*;
import Models.Domain.Builder.ContribucionBuilder.*;
import Models.Domain.FormasDeContribucion.*;
import Models.Domain.Personas.Colaborador;
import Models.Domain.Personas.Humano;
import Models.Domain.Personas.PersonaVulnerable;
import Models.Domain.Tarjeta.Tarjeta;
import lombok.Getter;


@Getter
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

        DonacionDeDineroBuilder builder = new DonacionDeDineroBuilder();

        FormaDeContribucion donacion = builder
                .monto(monto)
                .frecuencia(tipoFrecuencia)
                .construir();
        return  donacion;
    }


    public FormaDeContribucion donacionDeVianda(Object ... Context){

        this.checkUserRoleAndProceed(Rol.HUMANO);

        Vianda vianda = (Vianda) Context[1];
        Heladera heladera = (Heladera) Context[2];

        heladera.agregarVianda(vianda);

        DonacionDeViandaBuilder builder = new DonacionDeViandaBuilder();

        FormaDeContribucion donacion = builder
                .heladera(heladera)
                .vianda(vianda)
                .construir();


        return donacion;

    }

    public FormaDeContribucion distribucionDeVianda(Object ... Context){

        this.checkUserRoleAndProceed(Rol.HUMANO);

        Heladera heladeraOrigen = (Heladera) Context[1];
        Heladera heladeraDestino = (Heladera) Context[2];
        Integer cantidad = (Integer) Context[3];
        String motivo = (String) Context[4];


        DistribucionDeViandasBuilder builder = new DistribucionDeViandasBuilder();

        FormaDeContribucion donacion = builder
                .heladeraOrigen(heladeraOrigen)
                .heladeraDestino(heladeraDestino)
                .cantidadDeViandasAMover(cantidad)
                .motivos(motivo)
                .construir();

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

        HacerseCargoDeHeladeraBuilder hacerseCargoDeHeladeraBuilder = new HacerseCargoDeHeladeraBuilder();
        FormaDeContribucion donacion = hacerseCargoDeHeladeraBuilder
                .nombreCaracteristico(nombreCaracteristico)
                .tipoOrganizacion(tipoDeOrganizacion)
                .heladera(heladera)
                .construir();

        return donacion;
    }

    public  FormaDeContribucion ofrecerProducto(Object ... Context){
        this.checkUserRoleAndProceed(Rol.JURIDICO);

        Producto producto = (Producto) Context[1];
        Double puntosNecesarios = (Double) Context[2];
        Integer stock = (Integer) Context[3];


        OfrecerProductoBuilder builder = new OfrecerProductoBuilder();

        FormaDeContribucion donacion = builder
                .producto(producto)
                .stock(stock)
                .puntosNecesarios(puntosNecesarios)
                .construir();

        return donacion;
    }


    public FormaDeContribucion factoryMethod(Object ... Context){
        FormaDeContribucion contribucion = null;
        switch( (TipoDonacion) Context[0] ){
            case DONACION_DINERO: contribucion = this.donacionDeDinero( Context ); break;
            case DONACION_DE_VIANDA: contribucion = this.donacionDeVianda( Context ); break;
            case HACERSE_CARGO_DE_HELADERA: contribucion = this.hacerceCargoDeHeladera( Context ); break;
            case DISTRIBUCION_VIANDAS: contribucion = this.distribucionDeVianda( Context ); break;
            case ENTREGA_TARJETAS : contribucion = this.registrarTarjeta( Context ); break;
            case OFRECER_PRODUCTO: contribucion = this.ofrecerProducto( Context ); break;
        }
        return contribucion;
    }


}
