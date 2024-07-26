package Models.Domain.Heladera.Suscripciones.Sugerencia;

import java.util.ArrayList;
import java.util.List;

public class SugerenciaCompuesta implements Sugerencia {

    public SugerenciaCompuesta() {
        this.sugerencias = new ArrayList<>();
    }

    private List<Sugerencia> sugerencias;

    public void agregarSugerencia(Sugerencia sugerencia) {
        sugerencias.add(sugerencia);
    }

    @Override
    public String mostrar() {
        StringBuilder mensaje = new StringBuilder();
        for (Sugerencia sugerencia : sugerencias) {
            mensaje.append(sugerencia.mostrar() +"\n");
        }

        return mensaje.toString();
    }
}