package Models.Domain.FormasDeContribucion;

import Models.Domain.Heladera;
import Models.Domain.TipoDeOrganizacion;
import lombok.Setter;

import java.time.LocalDate;

@Setter
public class HacerseCargoDeHeladera extends FormaDeContribucion {
    private String nombreCaracteristico;
    private TipoDeOrganizacion tipoDeOrganizacion;
    private Heladera heladera;


    public HacerseCargoDeHeladera (){
    }

}
