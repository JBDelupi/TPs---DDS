package Models.Domain.Reporte;

import Models.Domain.Heladera.Heladera;
import Service.TareaDiferida.ChromeTask;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovimientoViandasPorHeladera  extends Reporte{


    public MovimientoViandasPorHeladera() {
        this.chromeTask = new ChromeTask();
        this.listaObjecto = new ArrayList<>();
    }


    public List<Object[]> obtenerListadoDeMovimientos(List<Object> objetos) {
        List<Object[]> listadoDeMovimientos = new ArrayList<>();

        for (Object obj : objetos) {
            if (obj instanceof Heladera) {
                Heladera heladera = (Heladera) obj;
                String direccion = heladera.getDireccion().toString();
                int cantidadDeViandasRetiradas = heladera.getCantidadDeviandasRetiradas();
                int cantidadDeViandasDepositadas = heladera.getViandas().size();

                listadoDeMovimientos.add(new Object[]{direccion, cantidadDeViandasRetiradas, cantidadDeViandasDepositadas});
            }
        }
        return listadoDeMovimientos;
    }


    public void armarReporte(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Reporte de Movimientos Por Heladera\n");
            writer.write("=============================================\n\n");


            for (Object[] heladeraMov : obtenerListadoDeMovimientos( listaObjecto)  ) {

                String direccion = (String) heladeraMov[0];
                int cantidadRetiradas = (int) heladeraMov[1];
                int cantidadDepositadas = (int) heladeraMov[2];
                writer.write("Heladera en: " + direccion);
                writer.write(" Viandas Depostiadas: " + cantidadDepositadas);
                writer.write(" Viandas Retiradas: " + cantidadRetiradas + "\n");
            }
        }
    }



}


