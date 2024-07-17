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

public class CantFallasPorHeladera {

    private List<Heladera> listasheladeras;
    private ChromeTask chromeTask;

    public CantFallasPorHeladera() {
        listasheladeras = new ArrayList<>();
         this.chromeTask = new ChromeTask();
       // chromeTask.ejecutarTareaPrograma(4000,this,"generarReporte");
    }

    public void activar(){
        chromeTask.ejecutarTareaPrograma(4000,this,"generarReporte");
    }

    public void desactivar(){
        chromeTask.pausarTarea();
    }

    public void cargarListasheladeras(Heladera heladera) {
        listasheladeras.add(heladera);
    }


    // Saco una lista de tuplas con dirección y cantidad de fallas
    public List<Object[]> obtenerListadoDeFallas(List<Heladera> heladeras) {
        List<Object[]> listadoDeFallas = new ArrayList<>();

        for (Heladera heladera : heladeras) {
            String direccion = heladera.getDireccion().toString();
            int cantidadDeFallas = heladera.getCantidadDeFallas();
            listadoDeFallas.add(new Object[]{direccion, cantidadDeFallas});
        }
        return listadoDeFallas;
    }

    // Genero el reporte en un archivo de texto
    public void armarReporte(List<Object[]> listadoDeFallas, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Reporte de Fallas por Heladera\n");
            writer.write("===============================\n\n");

            for (Object[] heladeraFalla : listadoDeFallas) {
                String direccion = (String) heladeraFalla[0];
                int cantidadDeFallas = (int) heladeraFalla[1];
                writer.write("Heladera en: " + direccion);
                writer.write("Cantidad de Fallas: " + cantidadDeFallas + "\n");
            }
        }
    }


    public void generarReporte() throws IOException {
        // obtengo listado heladeras
        List<Object[]> listadoDeFallas = obtenerListadoDeFallas(this.listasheladeras);

        try {
            String filePath = "reporte_fallas_" + LocalDate.now().toString() + ".txt";
            armarReporte(listadoDeFallas, filePath);
            System.out.println("Reporte generado: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
