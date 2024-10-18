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
    public void obtenerListado(List<Heladera> heladeras) {
        for (Heladera heladera : heladeras) {
            String direccion = heladera.getDireccion().getCalle() + " " +
                    heladera.getDireccion().getNumero()+ ", " +
                    heladera.getDireccion().getLocalidad();
            int cantidadDeFallas = heladera.getCantidadDeFallas();
            heladera.reestablecerFallas(); // reestablecer sus fallas para que empiece de cero para el siguiente reporte.
            getItems().add(new Object[]{direccion, cantidadDeFallas}); // agrego a los items
        }
    }
}

