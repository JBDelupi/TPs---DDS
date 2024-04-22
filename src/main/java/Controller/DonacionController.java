package Controller;

import Models.FormasDeContribucion.*;
import Models.Heladera;
import Models.Personas.Colaborador;
import Models.Personas.Humano;
import Models.Personas.Juridico;
import Models.TipoDeOrganizacion;
import Models.TipoFrecuencia;
import Models.Vianda;

public class DonacionController {
    FormaDeContribucion nuevaDonacion;
    Colaborador colaborador;


    public DonacionController(Colaborador colaborador){
        this.colaborador = colaborador;
    }

    private void checkUserRoleAndProceed(String tipoRol) {
       if(colaborador.getClass().getSimpleName() != tipoRol) {
           throw new UnauthorizedAccessException("El usuario no tiene el rol adecuado para realizar esta acción.");
       }

    }



    public void create(String context, Object ... Args){

        nuevaDonacion = this.factoryMethod(context,Args);
        colaborador.agregarNuevaDonacion(nuevaDonacion);
    }

    public FormaDeContribucion donacionDeDinero(String context, Double monto, TipoFrecuencia tipoFrecuencia){

        FormaDeContribucion donacion = new DonacionDeDinero(monto,tipoFrecuencia);

        return  donacion;
    }

    public FormaDeContribucion donacionDeVianda(String Context, Vianda vianda, Heladera heladera){

        this.checkUserRoleAndProceed( Humano.class.getSimpleName() );

        FormaDeContribucion donacion = new DonacionDeVianda(vianda,heladera);

        return donacion;

    }

    public FormaDeContribucion distribucionDeVianda(String Context, Heladera heladeraOrigen, Heladera heladeraDestino, Integer cantidadDeViandasAMover, String motivo){
        this.checkUserRoleAndProceed( Humano.class.getSimpleName() );

        FormaDeContribucion donacion = new DistribucionDeViandas(heladeraOrigen, heladeraDestino, cantidadDeViandasAMover, motivo);

        return donacion;
    }

    public FormaDeContribucion hacerceCargoDeHeladera(String Context, String nombreCaracteristico, TipoDeOrganizacion tipoDeOrganizacion, Heladera heladera){
        this.checkUserRoleAndProceed( Juridico.class.getSimpleName() );

        FormaDeContribucion donacion = new HacerseCargoDeHeladera(nombreCaracteristico, tipoDeOrganizacion, heladera);

        return donacion;
    }


    public FormaDeContribucion factoryMethod(String context) {
        return  factoryMethod(context,null);
    }

    public FormaDeContribucion factoryMethod(String context, Object ... Args){
        FormaDeContribucion contribucion = null;
        switch(context){
            case "1" : contribucion = this.donacionDeDinero(context, (Double) Args[0], (TipoFrecuencia) Args[1]); break;
            case "2" : contribucion = this.donacionDeVianda(context, (Vianda) Args[0], (Heladera) Args[1]); break;
            case "3" : contribucion = this.hacerceCargoDeHeladera(context, (String) Args[0], (TipoDeOrganizacion) Args[1], (Heladera) Args[2]); break;
            case "4" : contribucion = this.distribucionDeVianda(context, (Heladera) Args[0],(Heladera) Args[1], (Integer) Args[2], (String) Args[3]); break;

        }
        return contribucion;
    }

    public class UnauthorizedAccessException extends RuntimeException {
        public UnauthorizedAccessException(String message) {
            super(message);
        }
    }
}
