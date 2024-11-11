package Service.TareaDiferida;

import java.lang.reflect.Method;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class AdapterScheduledExecutorService {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> tareaProgramada;

    public void ejecutarTareaPrograma(int tiempo, Object objeto, String metodo) {
        if (tareaProgramada == null || tareaProgramada.isCancelled() || tareaProgramada.isDone()) {
            tareaProgramada = scheduler.scheduleAtFixedRate(() -> {
                try {
                    Method method = objeto.getClass().getMethod(metodo);
                    method.invoke(objeto);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, 0, tiempo, TimeUnit.SECONDS);
        }
    }

    public void pausarTareaProgramada() {
        if (tareaProgramada != null && !tareaProgramada.isCancelled()) {
            tareaProgramada.cancel(false);
        }
    }

    public boolean estaActiva() {
        return tareaProgramada != null && !tareaProgramada.isDone() && !tareaProgramada.isCancelled();
    }

    public void shutdown() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
