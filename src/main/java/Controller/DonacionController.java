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
        System.exit(403);
       }

    }



    public void create(String context, Object ... Args){

        nuevaDonacion = this.factoryMethod(context,Args[0],Args[1]);
        colaborador.agregarNuevaDonacion(nuevaDonacion);
    }

    public FormaDeContribucion donacionDeDinero(String context, Integer monto, TipoFrecuencia tipoFrecuencia){

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
    }


    public FormaDeContribucion factoryMethod(String context) {
        return  factoryMethod(context,null);
    }

    public FormaDeContribucion factoryMethod(String context, Object ... Args){
        FormaDeContribucion contribucion = null;
        switch(context){
            case "1" : contribucion = this.donacionDeDinero(context, (Integer) Args[0], (TipoFrecuencia) Args[1]); break;
            case "2" : contribucion = this.donacionDeVianda(context, (Vianda) Args[0], (Heladera) Args[1]); break;

        }
        return contribucion;
    }


}
