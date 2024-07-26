import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Suscripciones.Sugerencia.SistemaDeRedistribucion;
import Models.Domain.Heladera.Suscripciones.Sugerencia.Sugerencia;
import Models.Domain.Reporte.CantFallasPorHeladera;
import Models.Domain.Reporte.MovimientoViandasPorHeladera;
import Models.Domain.Reporte.TemplateReporte;
import Service.SistemaDeGeolocalizacion.PseudoBaseDatosHeladera;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {

        TemplateReporte<Heladera> unReporte = new MovimientoViandasPorHeladera();
        PseudoBaseDatosHeladera heladera = new PseudoBaseDatosHeladera();

        unReporte.cargarItems(heladera.baseHeladeras);
        unReporte.generarReporte();

        PseudoBaseDatosHeladera base = new PseudoBaseDatosHeladera();
        SistemaDeRedistribucion sistema = new SistemaDeRedistribucion(base.baseHeladeras);
        Sugerencia sugerencia = sistema.generarSugerencia(5);

        sugerencia.mostrar();
    }
}
