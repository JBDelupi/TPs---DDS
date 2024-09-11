package Controller;

import Controller.Administrador.AdministradorController;
import Controller.Administrador.HeladeraController;
import Controller.Administrador.VulnerableController;


public class FactoryController {

    public static Object controller(String nombre) {
        Object controller = null;
        switch (nombre) {
          case "Contribucion": controller = new ContribucionController( ); break;
          case "humano": controller =  new HumanoController(); break;
          case "login": controller =  new LoginController(); break;
          case "juridico": controller =  new JuridicoController(); break;
          case "heladeras": controller =  new HeladeraController(); break;
          case "vulnerable" : controller =  new VulnerableController(); break;
          case "incidente" : controller =  new FallaTecnicaController(); break;
          case "producto": controller =  new ProductoController(); break;
          case "puntos": controller =  new PuntoCercanoController(); break;
          case "admin": controller = new AdministradorController(); break;

        }
        return controller;
    }
}
