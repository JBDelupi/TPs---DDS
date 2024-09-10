package Models.Repository;

import Models.Domain.Heladera.Incidentes.Alerta;
import Service.Broker.Controllers.AlertaController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PseudoBaseDatosAlerta {
    private static PseudoBaseDatosAlerta instancia = null;

    public List<Alerta> base;

    public void agregar(Alerta... p) {
        Collections.addAll(this.base, p);
    }

    private PseudoBaseDatosAlerta() {
        this.base = new ArrayList<>();
    }

    public static PseudoBaseDatosAlerta getInstance() {
        if (instancia == null) {
            instancia = new PseudoBaseDatosAlerta();
        }
        return instancia;
    }


}
