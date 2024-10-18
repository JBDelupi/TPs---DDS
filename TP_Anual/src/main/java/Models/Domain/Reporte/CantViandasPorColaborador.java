package Models.Domain.Reporte;

import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Fisico;
import Models.Domain.Personas.Actores.TipoRol;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CantViandasPorColaborador extends TemplateReporte<Fisico> {

    @Override
    public void obtenerListado(List<Fisico> fisicos) {
        for (Fisico fisico : fisicos) {
            String nombre = fisico.getNombre();
            int cantidadDeViandas = ((Colaborador)fisico.getRol(TipoRol.COLABORADOR)).getCantidadViandasDonadas();
            getItems().add(new Object[]{nombre, cantidadDeViandas});
            ((Colaborador)fisico.getRol(TipoRol.COLABORADOR)).reestablecerViandas();
        }
    }


}