package Service.Server;

import Controller.*;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Router {
    public static void init(){

        Server.app().get("/",context -> context.render("main/index.hbs"));
        Server.app().get("/contact",context -> context.render("main/Contacto.hbs"));
        Server.app().get("/team",context -> context.render("main/team.hbs"));
        Server.app().get("/about",context -> context.render("main/about.hbs"));



        Server.app().get("/login", ((LoginController) FactoryController.controller("login"))::index);
        Server.app().post("/login", ((LoginController) FactoryController.controller("login"))::manejarInicioSesion);


        Server.app().get("/registro/humano", ((HumanoController) FactoryController.controller("humano"))::create);
        Server.app().post("/registro/humano", ((HumanoController) FactoryController.controller("humano"))::save);
        // Server.app().get("/", ((HumanoController) FactoryController.controller("humano"))::show);
        Server.app().get("/persona/humano/{id}", ((HumanoController) FactoryController.controller("humano"))::show);



        Server.app().get("/registro/juridica", ((JuridicoController) FactoryController.controller("juridico"))::create);
        Server.app().post("/registro/juridica", ((JuridicoController) FactoryController.controller("juridico"))::save);



        Server.app().get("/registro/heladera",((HeladeraController) FactoryController.controller("heladeras"))::create);
        Server.app().get("/heladeras",((HeladeraController) FactoryController.controller("heladeras"))::index);
        Server.app().get("/heladeras/{id}",((HeladeraController) FactoryController.controller("heladeras"))::show);
        Server.app().post("/registro/heladera",((HeladeraController) FactoryController.controller("heladeras"))::save);


        Server.app().get("/registro/vulnerable",((VulnerableController)FactoryController.controller("vulnerable"))::create);

        Server.app().get("/registro/incidente",((IncidenteController)FactoryController.controller("incidente"))::create);

        Server.app().get("/registro/producto",((ProductoController)FactoryController.controller("producto"))::create);
        Server.app().get("/productos",((ProductoController)FactoryController.controller("producto"))::index);
        Server.app().post("/registro/producto",((ProductoController)FactoryController.controller("producto"))::save);


    }

}
