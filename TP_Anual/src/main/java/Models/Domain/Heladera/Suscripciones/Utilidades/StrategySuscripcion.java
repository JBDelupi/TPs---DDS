package Models.Domain.Heladera.Suscripciones.Utilidades;

import Models.Domain.Heladera.Suscripciones.FaltanNViandasParaLlenar;
import Models.Domain.Heladera.Suscripciones.NViandasDisponibles;
import Models.Domain.Heladera.Suscripciones.ObserverHeladera;
import Models.Domain.Heladera.Suscripciones.SufrioDesperfecto;
import Models.Domain.Personas.Actores.Persona;

public class StrategySuscripcion {
    public static ObserverHeladera Strategy(String s, String numeroViandasStr, Persona Colaborador){

        int numeroViandas = numeroViandasStr != null && !numeroViandasStr.isEmpty() ? Integer.parseInt(numeroViandasStr) : 0;

        return switch (s) {
            case "faltanNViandas" -> new FaltanNViandasParaLlenar(Colaborador,numeroViandas);
            case "quedanNViandas" -> new NViandasDisponibles(Colaborador,numeroViandas);
            case "desperfecto" -> new SufrioDesperfecto(Colaborador);
            default -> null;
        };
    }
}
