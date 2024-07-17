import Models.Domain.Builder.HeladeraBuilder;
import Models.Domain.DatosPersonales.Direccion;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Reporte.CantFallasPorHeladera;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;


public class Main {
    public static void main(String[] args) {

        CantFallasPorHeladera cantFallasPorHeladera = new CantFallasPorHeladera();


        Heladera heladera = new Heladera();
        heladera.setDireccion(new Direccion());

        Heladera heladera2 = new Heladera();
        heladera2.setDireccion(new Direccion());

        Heladera heladera3 = new Heladera();
        heladera3.setDireccion(new Direccion());

        cantFallasPorHeladera.cargarListasheladeras(heladera);
        cantFallasPorHeladera.cargarListasheladeras(heladera2);
        cantFallasPorHeladera.cargarListasheladeras(heladera3);

        cantFallasPorHeladera.activar();

    }

}
