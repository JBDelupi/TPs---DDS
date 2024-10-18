package Controller.DTO;

import Models.Domain.Heladera.Suscripciones.FaltanNViandasParaLlenar;
import Models.Domain.Heladera.Suscripciones.NViandasDisponibles;
import Models.Domain.Heladera.Suscripciones.ObserverHeladera;
import Models.Domain.Heladera.Suscripciones.SufrioDesperfecto;
import lombok.Getter;
import java.util.Map;

@Getter
public class SuscripcionDTO {
    private String tipoSuscripcion;
    private Map<String, String> params;


    public SuscripcionDTO() {
    }

    public void Strategy(ObserverHeladera observerHeladera) {
        switch (tipoSuscripcion) {
            case "NviandasDisponibles": this.mapNviandasDisponible((NViandasDisponibles) observerHeladera); break;
            case "SufrioDesperfecto":  this.mapSufrioDesperfecto((SufrioDesperfecto) observerHeladera); break;
            case "FaltanNViandasParaLlenar": this.faltanNviandas((FaltanNViandasParaLlenar) observerHeladera); break;
        }
    }

    public void mapNviandasDisponible(NViandasDisponibles observerHeladera) {

    }
    public void mapSufrioDesperfecto(SufrioDesperfecto observerHeladera) {

    }
    public void faltanNviandas(ObserverHeladera observerHeladera) {

    }


}
