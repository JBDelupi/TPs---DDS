import Models.Domain.Builder.HeladeraBuilder;
import Models.Domain.DatosPersonales.Direccion;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Humano;
import Models.Domain.Reporte.CantFallasPorHeladera;
import Models.Domain.Reporte.CantViandasPorColaborador;
import Models.Domain.Reporte.MovimientoViandasPorHeladera;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.Test;

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

        Humano humano = new Humano();
        humano.setNombre("juan");
        humano.setCantidadViandasDonadas(8);

        Humano humano2 = new Humano();
        humano2.setNombre("jose");
        humano2.setCantidadViandasDonadas(25);

        CantViandasPorColaborador cantViandasPorColaborador = new CantViandasPorColaborador();
        cantViandasPorColaborador.cargarListashumanos(humano);
        cantViandasPorColaborador.cargarListashumanos(humano2);

        cantViandasPorColaborador.activar();

    }

    public static void main2(String[] args) {

        MovimientoViandasPorHeladera movimientoViandasPorHeladera = new MovimientoViandasPorHeladera();


        Heladera heladera = new Heladera();
        heladera.setDireccion(new Direccion());
        heladera.setCantidadDeviandasRetiradas(1);

        Heladera heladera2 = new Heladera();
        heladera2.setDireccion(new Direccion());
        heladera2.setCantidadDeviandasRetiradas(1);

        Heladera heladera3 = new Heladera();
        heladera3.setDireccion(new Direccion());
        heladera3.setCantidadDeviandasRetiradas(1);

        movimientoViandasPorHeladera.cargarListasheladeras(heladera);
        movimientoViandasPorHeladera.cargarListasheladeras(heladera2);
        movimientoViandasPorHeladera.cargarListasheladeras(heladera3);

        movimientoViandasPorHeladera.activar();


    }

}
