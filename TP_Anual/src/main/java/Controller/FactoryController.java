package Controller;

import Controller.Actores.Usuario;


public class FactoryController {

    public static Object controller(String nombre, Usuario usuario) {
        Object controller = null;
        switch (nombre) {
            case "Contribucion": controller = new ContribucionController( usuario); break;
            case "Humano": controller =  new HumanoController(usuario); break;
            case "Juridico": controller =  new JuridicoController(usuario); break;
        }
        return controller;
    }
}
