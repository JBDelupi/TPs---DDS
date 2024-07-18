package Models.Domain.Reporte;

import Models.Domain.Heladera.Heladera;
import Service.TareaDiferida.ChromeTask;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovimientoViandasPorHeladera {

    private List<Heladera> listasheladeras;
    private ChromeTask chromeTask;

    public MovimientoViandasPorHeladera() {
        listasheladeras = new ArrayList<>();
        this.chromeTask = new ChromeTask();
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
    public List<Object[]> obtenerListadoDeMovimientos(List<Heladera> heladeras) {
        List<Object[]> listadoDeMovimientos = new ArrayList<>();

        for (Heladera heladera : heladeras) {
            String direccion = heladera.getDireccion().toString();
            int cantidadDeviandasRetiradas = heladera.getCantidadDeviandasRetiradas();
            int cantidadDeViandasDepositadas = heladera.getViandas().size();
            listadoDeMovimientos.add(new Object[]{direccion, cantidadDeviandasRetiradas,cantidadDeViandasDepositadas});
        }
        return listadoDeMovimientos;
    }

    // Genero el reporte en un archivo de texto
    public void armarReporte(List<Object[]> listadoMovimientos, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Reporte de Movimientos Por Heladera\n");
            writer.write("=============================================\n\n");

            for (Object[] heladeraMov : listadoMovimientos) {
                String direccion = (String) heladeraMov[0];
                int cantidadRetiradas = (int) heladeraMov[1];
                int cantidadDepositadas = (int) heladeraMov[2];
                writer.write("Heladera en: " + direccion);
                writer.write(" Viandas Depostiadas: " + cantidadDepositadas);
                writer.write(" Viandas Retiradas: " + cantidadRetiradas + "\n");
            }
        }
    }


    public void generarReporte() throws IOException {
        // obtengo listado heladeras
        List<Object[]> listadoMovimientos = obtenerListadoDeMovimientos(this.listasheladeras);

        try {
            String filePath = "reporte_movimientos_" + LocalDate.now().toString() + ".txt";
            armarReporte(listadoMovimientos, filePath);
            System.out.println("Reporte generado: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


