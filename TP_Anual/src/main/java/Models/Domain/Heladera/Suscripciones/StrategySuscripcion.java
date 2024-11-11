package Models.Domain.Heladera.Suscripciones;

import Models.Domain.Personas.Actores.Persona;

public class StrategySuscripcion {
    public static ObserverHeladera Strategy(String s, int numeroViandasStr, Persona Colaborador){
        return switch (s) {
            case "faltanNViandas" -> new FaltanNViandasParaLlenar(Colaborador,numeroViandasStr);
            case "quedanNViandas" -> new NViandasDisponibles(Colaborador,numeroViandasStr);
            case "desperfecto" -> new SufrioDesperfecto(Colaborador);
            default -> null;
        };
    }
}
