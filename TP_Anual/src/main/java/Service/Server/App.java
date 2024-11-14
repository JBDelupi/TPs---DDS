package Service.Server;

import Controller.Actores.RolUsuario;
import Models.Domain.Personas.Actores.Fisico;
import Models.Domain.Personas.DatosPersonales.TipoDeDocumento;
import Models.Domain.Reporte.CantFallasPorHeladera;
import Models.Domain.Reporte.CantViandasPorColaborador;
import Models.Domain.Reporte.MovimientoViandasPorHeladera;
import Models.Domain.Reporte.TemplateReporte;
import Models.Repository.Dao;
import Models.Repository.RepoPersona;
import Service.DeccoSaludAPI.GeneradorReporteSalud;
import Service.Notificacion.Correo.CorreoAdapter;
import Service.TareaDiferida.ChromeTask;
import Service.Validador.CredencialDeAcceso;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) {
        Server.init();

        Fisico admin = new Fisico();
        admin.setNombre("Decco");
        admin.setApellido("Colaboraciones");
        admin.setNumeroDocumento("25102024");
        admin.setMedioDeNotificacion(new CorreoAdapter());
        admin.setCodigoDeNotificacion("admin.decco@gmail.com");
        admin.setTipoDeDocumento(TipoDeDocumento.DNI);
        admin.setCredencialDeAcceso(new CredencialDeAcceso("admin","admin"));
        admin.setTipoUsuario(RolUsuario.ADMINISTRADOR);
        Dao repo = new RepoPersona(Fisico.class);
        repo.agregar(admin);

        // Configuración del programador de tareas
     //   ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        // Programar el reporte para ejecutarse cada 7 días
     //   executorService.scheduleAtFixedRate(App::generarReporte, 0, 7, TimeUnit.DAYS);

    }

    public static void generarReporte()  {
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
