package Models.Domain.Reporte;

import Models.Domain.Personas.Actores.Humano;
import Service.TareaDiferida.AdapterChromeTask;
import Service.TareaDiferida.ChromeTask;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CantViandasPorColaborador {

    private List<Humano> listashumanos;
    private ChromeTask chromeTask;

    public CantViandasPorColaborador() {
        listashumanos = new ArrayList<>();
        this.chromeTask = new ChromeTask();
        // chromeTask.ejecutarTareaPrograma(4000, this, "generarReporte");
    }

    public void activar() {
        chromeTask.ejecutarTareaPrograma(4000, this, "generarReporte");
    }

    public void desactivar() {
        chromeTask.pausarTarea();
    }

    public void cargarListashumanos(Humano humano) {
        listashumanos.add(humano);
    }


    // Saco una lista de tuplas con nombre y cantidad de viandas
    public List<Object[]> obtenerListadoDeViandas(List<Humano> humanos) {
        List<Object[]> listadoDeViandas = new ArrayList<>();

        for (Humano humano : humanos) {
            String nombre = humano.getNombre();
            Integer cantidadDeViandas = humano.getCantidadViandasDonadas();
            listadoDeViandas.add(new Object[]{nombre, cantidadDeViandas});
            humano.reestablecerViandas();
        }
        return listadoDeViandas;
    }

    // Genero el reporte en un archivo de texto
    public void armarReporte(List<Object[]> listadoDeViandas, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Reporte de Viandas por Colaborador\n");
            writer.write("==================================\n\n");

            for (Object[] humanoVianda : listadoDeViandas) {
                String nombre = (String) humanoVianda[0];
                int cantidadDeViandas = (int) humanoVianda[1];
                writer.write("Colaborador: " + nombre + "\n");
                writer.write("Cantidad de Viandas: " + cantidadDeViandas + "\n\n");
            }
        }
    }

    public void generarReporte() throws IOException {
        // obtengo listado de colaboradores
        List<Object[]> listadoDeViandas = obtenerListadoDeViandas(this.listashumanos);

        try {
            String filePath = "reporte_viandas_" + LocalDate.now().toString() + ".txt";
            armarReporte(listadoDeViandas, filePath);
            System.out.println("Reporte generado: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
