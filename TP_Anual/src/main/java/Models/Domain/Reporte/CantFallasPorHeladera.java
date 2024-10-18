package Models.Domain.Reporte;

import Models.Domain.Heladera.Heladera;
import java.util.List;
import java.util.Arrays;

public class CantFallasPorHeladera extends TemplateReporte<Heladera> {

    @Override
    public void obtenerListado(List<Heladera> heladeras) {
        for (Heladera heladera : heladeras) {
            // Concatenar la dirección
            String direccion = heladera.getDireccion().getCalle() + " " +
                    heladera.getDireccion().getNumero() + ", " +
                    heladera.getDireccion().getLocalidad();

            // Obtener cantidad de fallas
            String cantidadDeFallas = String.valueOf(heladera.getCantidadDeFallas());

            // Restablecer las fallas para la siguiente ejecución
            heladera.reestablecerFallas();

            // Agregar a la lista de items en el formato adecuado
            getItems().add(Arrays.asList(direccion, cantidadDeFallas));
        }
    }
}
