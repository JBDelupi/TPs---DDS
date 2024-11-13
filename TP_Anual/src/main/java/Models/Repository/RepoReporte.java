package Models.Repository;


import Models.Domain.Reporte.TemplateReporte;
import Models.Repository.EntityManager.EntityManagerHelper;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

import static Models.Repository.EntityManager.EntityManagerHelper.createQuery;

public class RepoReporte extends Dao {
    public RepoReporte(Object type) {
        super(type);
    }

    public List buscarPorTipo(String tipoReporte) {
        // Suponiendo que 'tipoReporte' es el nombre de la subclase que deseas filtrar
        CriteriaBuilder builder = EntityManagerHelper.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TemplateReporte> criteria = builder.createQuery(TemplateReporte.class);
        Root<TemplateReporte> root = criteria.from(TemplateReporte.class);

        Join<TemplateReporte, List<String>> joinItems = root.join("items");

        // Filtra por subclase usando 'instanceof' o por el valor de 'tipo' en la tabla
        criteria.select(root).where(builder.equal(root.type(), tipoReporte));

        List<TemplateReporte> entities = EntityManagerHelper.getEntityManager().createQuery(criteria).getResultList();
        return entities;
    }

}
