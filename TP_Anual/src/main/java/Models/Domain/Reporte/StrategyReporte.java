package Models.Domain.Reporte;

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
