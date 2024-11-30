package Service.Observabilidad;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.Getter;

@Getter
public class MetricsRegistry {
    private static MetricsRegistry instance;
    private final MeterRegistry registry;

    private MetricsRegistry(MeterRegistry registry) {
        this.registry = registry;
    }

    public static void initialize(MeterRegistry registry) {
        if (instance == null) {
            instance = new MetricsRegistry(registry);
        }
    }

    public static MetricsRegistry getInstance() {
        if (instance == null) {
            throw new IllegalStateException("MetricsRegistry no está inicializado.");
        }
        return instance;
    }

}