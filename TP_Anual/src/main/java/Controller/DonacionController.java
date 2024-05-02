package Controller;

import Models.FormasDeContribucion.*;
import Models.Heladera;
import Models.Personas.*;
import Models.TipoDeOrganizacion;
import Models.TipoFrecuencia;
import Models.Vianda;

public class DonacionController extends Controller {
    FormaDeContribucion nuevaDonacion;


    public DonacionController(Usuario colaborador){
        this.usuario = colaborador;
    }


    public void create(Object ... Args){
        nuevaDonacion = this.factoryMethod(Args);
        usuario.agregarNuevaDonacion(nuevaDonacion);
    }

    public FormaDeContribucion donacionDeDinero(Double monto, TipoFrecuencia tipoFrecuencia){
        FormaDeContribucion donacion = new DonacionDeDinero(monto,tipoFrecuencia);
        return  donacion;
    }

    public FormaDeContribucion donacionDeVianda(Vianda vianda, Heladera heladera){

        this.checkUserRoleAndProceed(Rol.HUMANO);

        FormaDeContribucion donacion = new DonacionDeVianda(vianda,heladera);
        return donacion;

    }

    public FormaDeContribucion distribucionDeVianda(Heladera heladeraOrigen, Heladera heladeraDestino, Integer cantidadDeViandasAMover, String motivo){

        this.checkUserRoleAndProceed(Rol.HUMANO);
        FormaDeContribucion donacion = new DistribucionDeViandas(heladeraOrigen, heladeraDestino, cantidadDeViandasAMover, motivo);
        return donacion;
    }

    public FormaDeContribucion hacerceCargoDeHeladera(String nombreCaracteristico, TipoDeOrganizacion tipoDeOrganizacion, Heladera heladera){

        this.checkUserRoleAndProceed( Rol.JURIDICO );
        FormaDeContribucion donacion = new HacerseCargoDeHeladera(nombreCaracteristico, tipoDeOrganizacion, heladera);
        return donacion;
    }


    public FormaDeContribucion factoryMethod(Object ... Args){
        FormaDeContribucion contribucion = null;
        switch( (TipoDonacion) Args[0] ){
            case DONACION_DINERO: contribucion = this.donacionDeDinero( (Double) Args[1], (TipoFrecuencia) Args[2]); break;
            case DONACION_DE_VIANDA: contribucion = this.donacionDeVianda( (Vianda) Args[1], (Heladera) Args[2]); break;
            case HACERSE_CARGO_DE_HELADERA: contribucion = this.hacerceCargoDeHeladera( (String) Args[1], (TipoDeOrganizacion) Args[2], (Heladera) Args[3]); break;
            case DISTRIBUCION_VIANDAS: contribucion = this.distribucionDeVianda( (Heladera) Args[1],(Heladera) Args[2], (Integer) Args[3], (String) Args[4]); break;

        }
        return contribucion;
    }


}
