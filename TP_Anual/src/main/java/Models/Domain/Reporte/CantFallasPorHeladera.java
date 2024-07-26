package Models.Domain.Reporte;

import Models.Domain.Heladera.Heladera;
import Service.TareaDiferida.AdapterChromeTask;
import Service.TareaDiferida.ChromeTask;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CantFallasPorHeladera extends TemplateReporte<Heladera>{


    @Override
    protected List<Object[]> obtenerListado(List<Heladera> heladeras) {
        List<Object[]> listadoDeFallas = new ArrayList<>();
        for (Heladera heladera : heladeras) {
            String direccion = heladera.getDireccion().toString();
            int cantidadDeFallas = heladera.getCantidadDeFallas();
            listadoDeFallas.add(new Object[]{direccion, cantidadDeFallas});
        }
        return listadoDeFallas;
    }

    @Override
    protected String getFilePath() {
        return "reporte_fallas_" + LocalDate.now().toString() + ".txt";
    }

    @Override
    protected void armarReporte(List<Object[]> listado, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Reporte de Fallas por Heladera\n");
            writer.write("===============================\n\n");
            for (Object[] heladeraFalla : listado) {
                String direccion = (String) heladeraFalla[0];
                int cantidadDeFallas = (int) heladeraFalla[1];
                writer.write("Heladera en: " + direccion + "\n");
                writer.write("Cantidad de Fallas: " + cantidadDeFallas + "\n");
            }
        }
    }
}

