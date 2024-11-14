package Controller;

import Controller.Administrador.AdministradorController;
import Controller.Administrador.HeladeraController;
import Controller.Administrador.TecnicoController;
import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.OfrecerProducto;
import Models.Domain.FormasDeContribucion.Utilidades.Contribucion;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Incidentes.FallaTecnica;
import Models.Domain.Heladera.Incidentes.Incidente;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Reporte.TemplateReporte;
import Models.Domain.Tarjetas.TarjetaAlimentar;
import Models.Repository.RepoReporte;
import Models.Repository.RepoPersona;
import Models.Repository.*;
import Service.DeccoSaludAPI.DTO.Reporte.ReporteSalud;


public class FactoryController {

    public static Object controller(String nombre) {
        Object controller = null;
        switch (nombre) {
          case "Contribucion": controller = new ContribucionController( new RepoContribucion(Contribucion.class)); break;
          case "humano": controller =  new HumanoController( new RepoPersona(Persona.class) ); break;
          case "login": controller =  new LoginController( new RepoLogin(Persona.class)); break;
          case "juridico": controller =  new JuridicoController( new RepoPersona(Persona.class)); break;
          case "heladeras": controller =  new HeladeraController( new RepoHeladera(Heladera.class)); break;
          case "incidente" : controller =  new FallaTecnicaController(new RepoIncidente(Incidente.class)); break;
          case "producto": controller =  new ProductoController(new RepoContribucion(OfrecerProducto.class)); break;
          case "puntos": controller =  new PuntoCercanoController(); break;
          case "admin": controller = new AdministradorController(new RepoPersona(Persona.class)); break;
          case "tecnico": controller = new TecnicoController( new RepoTecnico(Persona.class)); break;
          case "recuperar": controller = new RecuperarController(); break;
          case "visitaTecnica": controller = new VisitaFallaTecnicaController( new RepoIncidente(FallaTecnica.class)); break;
          case "reporte": controller = new ReporteController( new RepoReporte(TemplateReporte.class)); break;
          case "deccosalud": controller = new DeccoSaludController( new RepoSalud(ReporteSalud.class)); break;
        }
        return controller;
    }
}
