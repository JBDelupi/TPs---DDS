package Models.Sensores.TareaDiferida;
import java.lang.reflect.Method;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AdapterScheduledExecutorService {

        private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        public void ejecutarTareaPrograma(int tiempo, Object objeto, String metodo) {
            scheduler.scheduleAtFixedRate(() -> {
                try {
                    Method method = objeto.getClass().getMethod(metodo);
                    method.invoke(objeto);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, 0, tiempo, TimeUnit.SECONDS);
        }


    public void pausarTareaProgramada() {
        if (scheduler != null && !scheduler.isShutdown()) {
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
}


