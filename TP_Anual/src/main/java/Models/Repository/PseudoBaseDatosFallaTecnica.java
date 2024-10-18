package Models.Repository;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Incidentes.FallaTecnica;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PseudoBaseDatosFallaTecnica {
    private static PseudoBaseDatosFallaTecnica instancia = null;

    public List<FallaTecnica> baseFallaTecnica;

    public void agregar(FallaTecnica... p) {
        Collections.addAll(this.baseFallaTecnica, p);
    }

    private PseudoBaseDatosFallaTecnica() {
        this.baseFallaTecnica = new ArrayList<>();
        // Adding 10 sample FallaTecnica objects
        this.addSampleFallas();
    }

    public static PseudoBaseDatosFallaTecnica getInstance() {
        if (instancia == null) {
            instancia = new PseudoBaseDatosFallaTecnica();
        }
        return instancia;
    }

    public FallaTecnica getId(String id) {
        return baseFallaTecnica.stream().filter(f -> f.getId().equals(Integer.parseInt(id))).findFirst().get();
    }

    // Method to add 10 sample FallaTecnica objects
    private void addSampleFallas() {
        for (int i = 1; i <= 10; i++) {
            FallaTecnica falla = new FallaTecnica();
            falla.setId(i);
          //  falla.setFoto("foto" + i + ".jpg");
            falla.setDescripcion("Descripción de la falla técnica " + i);
            falla.setSolucionado(i % 2 == 0); //
            falla.setFechaSolucionado(i % 2 == 0 ? LocalDateTime.now().minusDays(i) : null);
            falla.setFecha(LocalDateTime.now().minusDays(i * 2));
            Heladera h = new Heladera();
            h.setId(i);
            falla.setHeladera(h);

            this.baseFallaTecnica.add(falla);
        }
    }
}
