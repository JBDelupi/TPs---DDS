package Models.Domain.FormasDeContribucion.Utilidades.Model;

import java.util.HashMap;
import java.util.Map;

public class ContribucionStrategyFactory {
    private static final Map<String, ContribucionStrategy> strategies = new HashMap<>();

    static {
        strategies.put("donarViandas", new DonarViandasStrategy());
        strategies.put("distribucionViandas", new DistribucionViandasStrategy());
        strategies.put("hacerseCargoHeladera", new HacerseCargoHeladeraStrategy());
    }

    public static ContribucionStrategy getStrategy(String tipoContribucion) {
        ContribucionStrategy strategy = strategies.get(tipoContribucion);
        if (strategy == null) {
            strategy = new OtrosStrategy();
        }


        return strategy;
    }
}
