package Models.Domain.Reporte;

import Models.Domain.Personas.Actores.Fisico;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CantViandasPorColaborador extends TemplateReporte<Fisico> {

    @Override
    protected List<Object[]> obtenerListado(List<Fisico> fisicos) {
        List<Object[]> listadoDeViandas = new ArrayList<>();
        for (Fisico fisico : fisicos) {
            String nombre = fisico.getNombre();
            int cantidadDeViandas = fisico.getCantidadViandasDonadas();
            listadoDeViandas.add(new Object[]{nombre, cantidadDeViandas});
            fisico.reestablecerViandas();
        }
        return listadoDeViandas;
    }

    @Override
    protected String getFilePath() {
        return "";
    }

    @Override
    protected void armarReporte(List<Object[]> listado, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Reporte de Viandas por Colaborador\n");
            writer.write("==================================\n\n");
            for (Object[] humanoVianda : listado) {
                String nombre = (String) humanoVianda[0];
                int cantidadDeViandas = (int) humanoVianda[1];
                writer.write("Colaborador: " + nombre + "\n");
                writer.write("Cantidad de Viandas: " + cantidadDeViandas + "\n\n");
            }
        }
    }

}