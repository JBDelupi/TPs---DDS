package Service.Server;

import Models.Domain.Reporte.CantFallasPorHeladera;
import Models.Domain.Reporte.CantViandasPorColaborador;
import Models.Domain.Reporte.MovimientoViandasPorHeladera;
import Models.Domain.Reporte.TemplateReporte;
import Service.TareaDiferida.ChromeTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) {
        Server.init();

       /* Fisico admin = new Fisico();
        admin.setNombre("Decco");
        admin.setApellido("Colaboraciones");
        admin.setNumeroDocumento("25102024");
        admin.setTipoDeDocumento(TipoDeDocumento.DNI);
        admin.setCredencialDeAcceso(new CredencialDeAcceso("admin","admin"));
        admin.setTipoUsuario(RolUsuario.ADMINISTRADOR);
        Dao repo = new RepoPersona(Fisico.class);
        repo.agregar(admin); */

        // Configuración del programador de tareas
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        // Programar el reporte para ejecutarse cada 7 días
        executorService.scheduleAtFixedRate(App::generarReporte, 0, 7, TimeUnit.DAYS);

    }

    public static void generarReporte(){
        TemplateReporte reporte1 = new CantFallasPorHeladera();
        TemplateReporte reporte2 = new CantViandasPorColaborador();
        TemplateReporte reporte3 = new MovimientoViandasPorHeladera();

        reporte1.obtenerListado();
        reporte2.obtenerListado();
        reporte3.obtenerListado();

    }

}
