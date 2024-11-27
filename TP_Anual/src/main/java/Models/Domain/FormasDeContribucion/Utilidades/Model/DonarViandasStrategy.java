package Models.Domain.FormasDeContribucion.Utilidades.Model;

import Models.Repository.RepoContribucion;

import java.util.Map;

public class DonarViandasStrategy implements ContribucionStrategy{

    public void agregarModelo(Map<String, Object> model, RepoContribucion repo) {
        model.put("heladeras", repo.queryHeladeraDisponible());
    }
}
