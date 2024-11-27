package Models.Domain.FormasDeContribucion.Utilidades.Model;

import Models.Repository.RepoContribucion;

import java.util.Map;

public interface ContribucionStrategy {
    void agregarModelo(Map<String, Object> model, RepoContribucion repo);

}
