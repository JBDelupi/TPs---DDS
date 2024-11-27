package Models.Domain.Reporte;

import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Fisico;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.Actores.TipoRol;
import Models.Repository.RepoPersona;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Arrays;


@Entity
@DiscriminatorValue("CantViandasPorColaborador")
@NoArgsConstructor
public class CantViandasPorColaborador extends TemplateReporte {

    public  void obtenerListado() {

        RepoPersona repoPersona = new RepoPersona();
        List<Fisico> fisicos = repoPersona.fisicoRol(TipoRol.COLABORADOR);

        for (Fisico fisico : fisicos) {
            String nombre = fisico.getNombre();

            Colaborador colaborador = (Colaborador) fisico.getRol(TipoRol.COLABORADOR);
            String cantidadDeViandas = String.valueOf(colaborador.getCantidadViandasDonadas());

            getItems().add(Arrays.asList(nombre, cantidadDeViandas));

            colaborador.reestablecerViandas();
        }
        repoPersona.agregar(this);

    }
}
