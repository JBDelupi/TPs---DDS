package Service.Server;

import Controller.FactoryController;
import Controller.HumanoController;
import Controller.JuridicoController;
import Controller.LoginController;
import Controller.HeladeraController;

public class Router {
    public static void init(){
        Server.app().get("/login", ((LoginController) FactoryController.controller("login"))::create);
        Server.app().get("/", ((LoginController) FactoryController.controller("login"))::show);


        Server.app().get("/registro/humano", ((HumanoController) FactoryController.controller("humano"))::create);
        Server.app().post("/registro/humano", ((HumanoController) FactoryController.controller("humano"))::save);
        // Server.app().get("/", ((HumanoController) FactoryController.controller("humano"))::show);

        Server.app().get("/registro/juridica", ((JuridicoController) FactoryController.controller("juridico"))::create);
        Server.app().post("/registro/juridica", ((JuridicoController) FactoryController.controller("juridico"))::save);


        Server.app().get("/fridge/map", ((HeladeraController) FactoryController.controller("heladeras"))::index);
        Server.app().get("/index/humano", ((LoginController) FactoryController.controller("index"))::index);

    }

}
