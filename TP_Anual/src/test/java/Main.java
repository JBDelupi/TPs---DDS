import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Suscripciones.Sugerencia.SistemaDeRedistribucion;
import Models.Domain.Heladera.Suscripciones.Sugerencia.Sugerencia;
import Models.Domain.Reporte.MovimientoViandasPorHeladera;
import Models.Domain.Reporte.TemplateReporte;
import Models.Repository.PseudoBaseDatosHeladera;
import Service.APIPuntos.Punto;
import Service.APIPuntos.ServicioPuntosAPI;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        List<Punto> puntosList = ServicioPuntosAPI.getInstance().obtenerPuntosEstrategicos(new Punto(),"2");
    }
}
