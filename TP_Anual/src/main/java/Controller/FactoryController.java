package Controller;

import Controller.Actores.Usuario;


public class FactoryController {

    public static Object controller(String nombre) {
        Object controller = null;
        switch (nombre) {
          //  case "Contribucion": controller = new ContribucionController( ); break;
          case "humano": controller =  new HumanoController(); break;
          case "login": controller =  new LoginController(); break;
          case "juridico": controller =  new JuridicoController(); break;
          case "heladeras": controller =  new HeladeraController(); break;
          case "index": controller =  new LoginController(); break;
        }
        return controller;
    }
}
