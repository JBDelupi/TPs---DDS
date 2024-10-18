package Models.Repository;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.DatosPersonales.Direccion;
import Models.Domain.Reporte.MovimientoViandasPorHeladera;
import Models.Domain.Reporte.TemplateReporte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PseudoBaseDatosReportes {

    // Instancia singleton
    private static PseudoBaseDatosReportes instancia;

    // Almacén de reportes simulados
    private final Map<String, TemplateReporte<?>> reportes;

    private PseudoBaseDatosReportes() {
        reportes = new HashMap<>();
        cargarDatosIniciales(); // Cargar algunos reportes de prueba
    }

    // Obtener instancia singleton
    public static PseudoBaseDatosReportes getInstance() {
        if (instancia == null) {
            instancia = new PseudoBaseDatosReportes();
        }
        return instancia;
    }

    // Método para cargar algunos reportes de prueba
    private void cargarDatosIniciales() {
        // Crear un ejemplo de MovimientoViandasPorHeladera
        MovimientoViandasPorHeladera reporteViandas = new MovimientoViandasPorHeladera();
        reporteViandas.obtenerListado(generarHeladerasPrueba());
        String idReporte = UUID.randomUUID().toString(); // Generar ID único
        reporteViandas.getItems().add(new Object[]{"Calle Falsa 123", 10, 8});
        reporteViandas.getItems().add(new Object[]{"Calle Verdadera 456", 15, 7});
        reportes.put(idReporte, reporteViandas);
    }

    // Simulación de heladeras para el reporte
    private List<Heladera> generarHeladerasPrueba() {
        List<Heladera> heladeras = new ArrayList<>();
        Heladera heladera1 = new Heladera();
        Heladera heladera2 = new Heladera();

        Direccion direccion = new Direccion();
        direccion.setCalle("Medrano");
        direccion.setNumero("231");
        direccion.setLocalidad("Almagro");

        heladera1.setDireccion(direccion);
        heladera2.setDireccion(direccion);

        heladeras.add(heladera1);
        heladeras.add(heladera2);
        return heladeras;
    }

    // Obtener reportes por tipo (simulación)
    public List<TemplateReporte<?>> getReportesDeTipo(String tipo) {
        List<TemplateReporte<?>> reportesFiltrados = new ArrayList<>();
        for (TemplateReporte<?> reporte : reportes.values()) {
            if (reporte.getClass().getSimpleName().equalsIgnoreCase(tipo)) {
                reportesFiltrados.add(reporte);
            }
        }
        return reportesFiltrados;
    }

    // Obtener reporte por ID
    public TemplateReporte<?> getReportesPorID(String id) {
        return reportes.get(id);
    }
}
