import Models.Domain.Personas.DatosPersonales.Direccion;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Humano;
import Models.Domain.Reporte.CantFallasPorHeladera;
import Models.Domain.Reporte.CantViandasPorColaborador;
import Models.Domain.Reporte.MovimientoViandasPorHeladera;
import Models.Domain.Reporte.Reporte;
import Service.SistemaDeGeolocalizacion.PseudoBaseDatosHeladera;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {

        Reporte unReporte = new MovimientoViandasPorHeladera();
        PseudoBaseDatosHeladera heladera = new PseudoBaseDatosHeladera();

        unReporte.agregarListaParaReporte(heladera.baseHeladeras.get(0),heladera.baseHeladeras.get(1));
        unReporte.generarReporte();


    }

}
