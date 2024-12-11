package Service.Server;


import Models.Domain.Reporte.CantFallasPorHeladera;
import Models.Domain.Reporte.CantViandasPorColaborador;
import Models.Domain.Reporte.MovimientoViandasPorHeladera;
import Models.Domain.Reporte.TemplateReporte;
import Models.Repository.RepoReporte;
import Service.Broker.RabbitMQAdapter;
import Service.DeccoSaludAPI.GeneradorReporteSalud;
import Service.Observabilidad.DDMetricsUtils;
import Service.Observabilidad.MetricsRegistry;


public class App {

    public static void main(String[] args) throws Exception {
        Server.init();
        RabbitMQAdapter.getInstance().init();
        DDMetricsUtils metricsUtils = new DDMetricsUtils("App-Decco");
        MetricsRegistry.initialize(metricsUtils.getRegistry());
        System.out.println("Métricas básicas inicializadas.");

        generarReporte();


    }

    public static void generarReporte()  {
        if(!RepoReporte.generoElReporte()){
            generacionManual();
        }
    }

    public static void generacionManual(){
        TemplateReporte reporte1 = new CantFallasPorHeladera();
        TemplateReporte reporte2 = new CantViandasPorColaborador();
        TemplateReporte reporte3 = new MovimientoViandasPorHeladera();
        GeneradorReporteSalud reporteSalud = new GeneradorReporteSalud();

        reporte1.obtenerListado();
        reporte2.obtenerListado();
        reporte3.obtenerListado();
        try {
            reporteSalud.generarReporte();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
