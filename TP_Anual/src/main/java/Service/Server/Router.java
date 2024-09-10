package Service.Server;

import Controller.*;
import Controller.Administrador.AdministradorController;
import Controller.Administrador.HeladeraController;
import Controller.Administrador.VulnerableController;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;

public class Router {
    public static void init(){

        Server.app().get("/",context -> context.render("main/index.hbs"));
        Server.app().get("/contact",context -> context.render("main/Contacto.hbs"));
        Server.app().get("/team",context -> context.render("main/team.hbs"));
        Server.app().get("/about",context -> context.render("main/about.hbs"));
        Server.app().get("/registro",context -> context.render("sesion/registro.hbs"));


        Server.app().routes(()->{
            post("/logout", ((LoginController)FactoryController.controller("login"))::manejarCierreSesion);
            get("/login", ((LoginController) FactoryController.controller("login"))::index);
            post("/login", ((LoginController) FactoryController.controller("login"))::manejarInicioSesion);
        });

        Server.app().routes(()->{
            get("/index/juridico", ((JuridicoController) FactoryController.controller("juridico"))::index);
            get("/registro/juridica", ((JuridicoController) FactoryController.controller("juridico"))::create);
            post("/registro/juridica", ((JuridicoController) FactoryController.controller("juridico"))::save);
        });

        Server.app().routes(()->{
            get("/registro/heladera",((HeladeraController) FactoryController.controller("heladeras"))::create);
            get("/heladeras",((HeladeraController) FactoryController.controller("heladeras"))::index);
            get("/heladeras/{id}",((HeladeraController) FactoryController.controller("heladeras"))::show);
            post("/registro/heladera",((HeladeraController) FactoryController.controller("heladeras"))::save);
        });

        Server.app().routes(()->{
            get("/index/humano",((HumanoController) FactoryController.controller("humano"))::index );
            get("/registro/humano", ((HumanoController) FactoryController.controller("humano"))::create);
            post("/registro/humano", ((HumanoController) FactoryController.controller("humano"))::save);
            get("/persona/humano/{id}", ((HumanoController) FactoryController.controller("humano"))::show);
        });

        Server.app().routes(()->{
            get("/incidentes",((FallaTecnicaController) FactoryController.controller("incidente"))::index);
            get("/registro/incidente",((FallaTecnicaController)FactoryController.controller("incidente"))::create);
            post("/registro/incidente",((FallaTecnicaController)FactoryController.controller("incidente"))::save);
            get("/incidentes/{id}", ((FallaTecnicaController)FactoryController.controller("incidente"))::show);
        });

        Server.app().routes(()->{
            get("/registro/producto",((ProductoController)FactoryController.controller("producto"))::create);
            post("/registro/producto",((ProductoController)FactoryController.controller("producto"))::save);
            get("/productos",((ProductoController)FactoryController.controller("producto"))::index);
            get("/productos/{id}",((ProductoController)FactoryController.controller("producto"))::show);
        });


        Server.app().get("/registro/vulnerable",((VulnerableController)FactoryController.controller("vulnerable"))::create);


       Server.app().routes(()->{
           get("/contribuciones",((ContribucionController)FactoryController.controller("Contribucion"))::index);
           get("/contribuciones/{id}",((ContribucionController)FactoryController.controller("Contribucion"))::create);

       });

        Server.app().routes(()->{
            // Pantallas de admins
           // get("admin/login", ((AdministradorController) FactoryController.controller("admin"))::show);
           // post("admin/login", ((AdministradorController) FactoryController.controller("admin"))::login);
            get("admin/index", ((AdministradorController) FactoryController.controller("admin"))::index);

        });


    }

}
