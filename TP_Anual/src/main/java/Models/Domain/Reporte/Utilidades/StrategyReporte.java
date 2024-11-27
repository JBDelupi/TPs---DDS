package Models.Domain.Reporte.Utilidades;

import Models.Domain.Reporte.CantFallasPorHeladera;
import Models.Domain.Reporte.CantViandasPorColaborador;
import Models.Domain.Reporte.MovimientoViandasPorHeladera;
import Models.Domain.Reporte.TemplateReporte;

public class StrategyReporte {
    public static TemplateReporte strategy(String tipo) {
        return switch (tipo) {
            case "cantidadDeFallasPorHeladera" -> new CantFallasPorHeladera();
            case "movimientoDeViandasPorHeladera" -> new MovimientoViandasPorHeladera();
            case "cantidadDeViandasPorColaborador" -> new CantViandasPorColaborador();
            default -> null;
        };
    }
}
