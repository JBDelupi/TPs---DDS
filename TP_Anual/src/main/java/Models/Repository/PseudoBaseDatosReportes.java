package Models.Repository;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.DatosPersonales.Direccion;
import Models.Domain.Reporte.MovimientoViandasPorHeladera;
import Models.Domain.Reporte.TemplateReporte;

import java.util.ArrayList;
import java.util.List;

public class PseudoBaseDatosReportes {

    // Instancia singleton
    private static PseudoBaseDatosReportes instancia;

    // Almacén de reportes simulados
    private final List<TemplateReporte<?>> reportes;

    // Constructor privado para asegurar el patrón Singleton
    private PseudoBaseDatosReportes() {
        reportes = new ArrayList<>();
        cargarDatosIniciales();
    }

    // Obtener instancia singleton
    public static PseudoBaseDatosReportes getInstance() {
        if (instancia == null) {
            instancia = new PseudoBaseDatosReportes();
        }
        return instancia;
    }

    // Cargar algunos reportes de prueba al iniciar
    private void cargarDatosIniciales() {
        // Crear un ejemplo de MovimientoViandasPorHeladera
        MovimientoViandasPorHeladera reporteViandas = new MovimientoViandasPorHeladera();
        reporteViandas.obtenerListado(generarHeladerasPrueba());

        // Asignar ID al reporte y agregarlo a la lista de reportes
        reporteViandas.setId(3);
        reportes.add(reporteViandas);
    }

    // Simulación de heladeras para el reporte
    private List<Heladera> generarHeladerasPrueba() {
        List<Heladera> heladeras = new ArrayList<>();
        Heladera heladera1 = new Heladera();
        Heladera heladera2 = new Heladera();

        // Crear una dirección de ejemplo
        Direccion direccion = new Direccion();
        direccion.setCalle("Medrano");
        direccion.setNumero("231");
        direccion.setLocalidad("Almagro");

        // Asignar la misma dirección a las heladeras de prueba
        heladera1.setDireccion(direccion);
        heladera2.setDireccion(direccion);

        // Configurar las viandas colocadas y retiradas
        heladera1.setCantidadDeviandasDepositadas(24);
        heladera2.setCantidadDeviandasDepositadas(41);
        heladera1.setCantidadDeviandasRetiradas(4);
        heladera2.setCantidadDeviandasRetiradas(11);

        // Agregar las heladeras a la lista
        heladeras.add(heladera1);
        heladeras.add(heladera2);
        return heladeras;
    }

    // Obtener reportes por tipo
    public List<TemplateReporte<?>> getReportesDeTipo(String tipo) {
        List<TemplateReporte<?>> reportesFiltrados = new ArrayList<>();
        for (TemplateReporte<?> reporte : reportes) {
            if (reporte.getClass().getSimpleName().equalsIgnoreCase(tipo)) {
                reportesFiltrados.add(reporte);
            }
        }
        return reportesFiltrados;
    }

    // Obtener un reporte específico por ID
    public TemplateReporte<?> getReportesPorID(String id) {
        for (TemplateReporte<?> reporte : reportes) {
            if (reporte.getId().equals(id)) {
                return reporte;
            }
        }
        return null;  // Retornar null si no se encuentra el reporte
    }
}
