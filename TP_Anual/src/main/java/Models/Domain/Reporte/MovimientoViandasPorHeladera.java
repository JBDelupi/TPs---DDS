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
    public void obtenerListado(List<Heladera> heladeras) {
        for (Heladera heladera : heladeras) {
            String direccion = heladera.getDireccion().getCalle() + " " +
                    heladera.getDireccion().getNumero()+ ", " +
                    heladera.getDireccion().getLocalidad();
            int cantidadDeViandasDepositadas = heladera.getCantidadDeviandasDepositadas();
            int cantidadDeViandasRetiradas = heladera.getCantidadDeviandasRetiradas();
            heladera.reestablecerViandasRetiradas();
            heladera.reestablecerViandasDepositadas();
            getItems().add(new Object[]{direccion, cantidadDeViandasDepositadas, cantidadDeViandasRetiradas });
        }
    }
}


