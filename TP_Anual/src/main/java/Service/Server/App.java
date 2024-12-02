package Service.Server;

import Controller.Actores.RolUsuario;
import Models.Domain.Excepciones.*;
import Models.Domain.Personas.Actores.Fisico;
import Models.Domain.Personas.DatosPersonales.TipoDeDocumento;
import Models.Domain.Reporte.CantFallasPorHeladera;
import Models.Domain.Reporte.CantViandasPorColaborador;
import Models.Domain.Reporte.MovimientoViandasPorHeladera;
import Models.Domain.Reporte.TemplateReporte;
import Models.Repository.Dao;
import Models.Repository.RepoPersona;
import Models.Repository.RepoReporte;
import Service.Broker.RabbitMQAdapter;
import Service.DeccoSaludAPI.GeneradorReporteSalud;
import Service.Notificacion.Correo.CorreoAdapter;
import Service.Observabilidad.DDMetricsUtils;
import Service.Observabilidad.MetricsRegistry;
import Service.Server.exceptions.AccessDeniedException;
import Service.Server.exceptions.InvalidPasswordException;
import Service.Server.exceptions.UnauthorizedResponseException;
import Service.Server.exceptions.UserAlreadyExistsException;
import Service.Server.handlers.AccessDeniedHandler;
import Service.Server.handlers.PuntosInsuficientesHandler;
import Service.TareaDiferida.ChromeTask;
import Service.Validador.CredencialDeAcceso;
import Service.Validador.Encriptador;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InvalidClassException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) throws Exception {
        Server.init();
        RabbitMQAdapter.getInstance().init();
        DDMetricsUtils metricsUtils = new DDMetricsUtils("App-Decco");
        MetricsRegistry.initialize(metricsUtils.getRegistry());
        System.out.println("Métricas básicas inicializadas.");

        generarReporte();



        System.out.println( System.getenv("DDMETRICS_TOKEN"));

    }

    public static void generarReporte()  {


        if(!RepoReporte.generoElReporte()){

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

}
