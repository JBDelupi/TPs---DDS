package Models.Domain.Reporte;

import Models.Domain.Heladera.Heladera;
import Service.TareaDiferida.ChromeTask;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovimientoViandasPorHeladera  extends TemplateReporte<Heladera> {



    @Override
    public List<Object[]> obtenerListado(List<Heladera> heladeras) {
        List<Object[]> listadoDeMovimientos = new ArrayList<>();
        for (Heladera heladera : heladeras) {
            String direccion = heladera.getDireccion().getCalle() + " " +
                    heladera.getDireccion().getNumero()+ ", " +
                    heladera.getDireccion().getLocalidad();
            int cantidadDeViandasRetiradas = heladera.getCantidadDeviandasRetiradas();
            int cantidadDeViandasDepositadas = heladera.getViandas().size();
            listadoDeMovimientos.add(new Object[]{direccion, cantidadDeViandasRetiradas, cantidadDeViandasDepositadas});
        }
        return listadoDeMovimientos;
    }

    @Override
    protected String getFilePath() {
        return "reporte_movimientos_" + LocalDate.now().toString() + ".txt";
    }

    @Override
    protected void armarReporte(List<Object[]> listado, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Reporte de Movimientos Por Heladera\n");
            writer.write("=============================================\n\n");
            for (Object[] heladeraMov : listado) {
                String direccion = (String) heladeraMov[0];
                int cantidadRetiradas = (int) heladeraMov[1];
                int cantidadDepositadas = (int) heladeraMov[2];
                writer.write("Heladera en: " + direccion + "\n");
                writer.write("Viandas Depositadas: " + cantidadDepositadas + "\n");
                writer.write("Viandas Retiradas: " + cantidadRetiradas + "\n\n");
            }
        }
    }



}


