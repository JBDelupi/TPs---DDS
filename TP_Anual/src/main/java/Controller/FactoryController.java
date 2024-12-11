package Controller;


import Models.Repository.RepoReporte;
import Models.Repository.RepoPersona;
import Models.Repository.*;


public class FactoryController {

    public static Object controller(String nombre) {
        Object controller = null;
        switch (nombre) {
          case "Contribucion": controller = new ContribucionController( new RepoContribucion()); break;
          case "humano": controller =  new HumanoController( new RepoPersona() ); break;
          case "login": controller =  new LoginController( new RepoLogin()); break;
          case "juridico": controller =  new JuridicoController( new RepoPersona()); break;
          case "heladeras": controller =  new HeladeraController( new RepoHeladera()); break;
          case "incidente" : controller =  new FallaTecnicaController(new RepoIncidente()); break;
          case "producto": controller =  new ProductoController(new RepoContribucion()); break;
          case "puntos": controller =  new PuntoCercanoController(); break;
          case "admin": controller = new AdministradorController(new RepoPersona()); break;
          case "tecnico": controller = new TecnicoController( new RepoTecnico()); break;
          case "reporte": controller = new ReporteController( new RepoReporte()); break;
          case "deccosalud": controller = new DeccoSaludController( new RepoSalud()); break;
          case "loginSSO": controller = new LoginSSOController(new RepoPersona()); break;
        }
        return controller;
    }
}
