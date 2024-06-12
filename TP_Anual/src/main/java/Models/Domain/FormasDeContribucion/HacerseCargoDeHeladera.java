package Models.Domain.FormasDeContribucion;

import Models.Domain.Heladera;
import Models.Domain.TipoDeOrganizacion;

import java.time.LocalDate;

public class HacerseCargoDeHeladera extends FormaDeContribucion {
    private String nombreCaracteristico;
    private TipoDeOrganizacion tipoDeOrganizacion;
    private Heladera heladera;

    public HacerseCargoDeHeladera (String nombreCaracteristico, TipoDeOrganizacion tipoDeOrganizacion, Heladera heladera){
        this.nombreCaracteristico = nombreCaracteristico;
        this.tipoDeOrganizacion = tipoDeOrganizacion;
        this.heladera = heladera;
        this.fechaDeDonacion = LocalDate.now();
    }

    public HacerseCargoDeHeladera (){

    }

}
