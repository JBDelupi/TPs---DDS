package Models.Domain.FormasDeContribucion.Utilidades.Model;

import Models.Repository.RepoContribucion;

import java.util.Map;

public class HacerseCargoHeladeraStrategy implements ContribucionStrategy{
    @Override
    public void agregarModelo(Map<String, Object> model, RepoContribucion repo) {
        model.put("heladeras", repo.queryHeladeraDuenia());
    }
}
