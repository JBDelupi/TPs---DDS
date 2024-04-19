package Controller;

import Models.FormasDeContribucion.*;
import Models.Heladera;
import Models.Personas.Colaborador;
import Models.TipoFrecuencia;
import Models.Vianda;

public class DonacionController {
    FormaDeContribucion nuevaDonacion;
    Colaborador colaborador;

    public DonacionController(Colaborador colaborador){
        this.colaborador = colaborador;
    }

    public void create(String context, Object ... Args){

        nuevaDonacion = this.factoryMethod(context,Args[0],Args[1]);
        colaborador.agregarNuevaDonacion(nuevaDonacion);
        System.out.println("Lo actualiza en la base de datos");
        System.out.println("Reedirigo");
    }

    public FormaDeContribucion donacionDeDinero(String context, Integer monto, TipoFrecuencia tipoFrecuencia){

        Integer unMonto = monto; // Lo trae el context
        TipoFrecuencia unTipoFrecuencia = tipoFrecuencia; // Lo trae el context

        FormaDeContribucion donacion = new DonacionDeDinero(monto,tipoFrecuencia);

        return  donacion;
    }

    public FormaDeContribucion donacionDeVianda(String Context, Vianda vianda, Heladera heladera){

        Heladera unaHeladera = heladera; // Lo trae el context
        Vianda unaVianda = vianda; // Lo trae el context

        FormaDeContribucion donacion = new DonacionDeVianda(vianda,heladera);

        return donacion;

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
