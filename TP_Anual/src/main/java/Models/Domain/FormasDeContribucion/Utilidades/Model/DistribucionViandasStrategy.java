package Models.Domain.FormasDeContribucion.Utilidades.Model;

import Models.Domain.Heladera.Heladera;
import Models.Repository.RepoContribucion;

import java.util.List;
import java.util.Map;

public class DistribucionViandasStrategy implements ContribucionStrategy{
    @Override
    public void agregarModelo(Map<String, Object> model, RepoContribucion repo) {
        List<Heladera> heladeraList = repo.buscarTodos(Heladera.class);
        model.put("heladeras", heladeraList.stream().filter(f->!f.getViandas().isEmpty()).toList());
        model.put("heladerasDisponible", heladeraList.stream().filter(f->!f.getEstaLlena()).toList());
    }

}
