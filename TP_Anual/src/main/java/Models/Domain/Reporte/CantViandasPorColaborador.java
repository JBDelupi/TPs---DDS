package Models.Domain.Reporte;

import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Fisico;
import Models.Domain.Personas.Actores.TipoRol;
import java.util.List;
import java.util.Arrays;

public class CantViandasPorColaborador extends TemplateReporte<Fisico> {

    @Override
    public void obtenerListado(List<Fisico> fisicos) {
        for (Fisico fisico : fisicos) {
            String nombre = fisico.getNombre();

            Colaborador colaborador = (Colaborador) fisico.getRol(TipoRol.COLABORADOR);
            String cantidadDeViandas = String.valueOf(colaborador.getCantidadViandasDonadas());

            getItems().add(Arrays.asList(nombre, cantidadDeViandas));

            colaborador.reestablecerViandas();
        }
    }
}
