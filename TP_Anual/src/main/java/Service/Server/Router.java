package Service.Server;

import Controller.*;
import Controller.Administrador.AdministradorController;
import Controller.Administrador.HeladeraController;
import Controller.Administrador.TecnicoController;
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

        Server.app().get("registro/puntos",((PuntoCercanoController)FactoryController.controller("puntos"))::index);
        Server.app().post("registro/puntos",((PuntoCercanoController)FactoryController.controller("puntos"))::cargarPuntos);


        Server.app().routes(()->{
            post("/logout", ((LoginController)FactoryController.controller("login"))::manejarCierreSesion);
            get("/login", ((LoginController) FactoryController.controller("login"))::index);
            post("/login", ((LoginController) FactoryController.controller("login"))::manejarInicioSesion);
        });

        Server.app().routes(()->{
            get("/recuperar", ((RecuperarController) FactoryController.controller("recuperar"))::index); //para el olvide mi contrasena
        });

        Server.app().routes(()->{
            get("/index/juridico", ((JuridicoController) FactoryController.controller("juridico"))::index);
            get("/registro/juridico", ((JuridicoController) FactoryController.controller("juridico"))::create);
            post("/registro/juridico", ((JuridicoController) FactoryController.controller("juridico"))::save);
            get("/juridico/contribuciones/{id}", ((JuridicoController) FactoryController.controller("juridico"))::consultarContribuciones);
            get("/persona/juridico/{id}", ((JuridicoController) FactoryController.controller("juridico"))::show);
        });

        Server.app().routes(()->{
            get("/registro/heladera",((HeladeraController) FactoryController.controller("heladeras"))::create);
            get("/heladeras",((HeladeraController) FactoryController.controller("heladeras"))::index);
            get("/heladeras/{id}",((HeladeraController) FactoryController.controller("heladeras"))::show);
            post("/registro/heladera",((HeladeraController) FactoryController.controller("heladeras"))::save);
        });

        Server.app().routes(()->{
            get("/index/fisico",((HumanoController) FactoryController.controller("humano"))::index );
            get("/registro/fisico", ((HumanoController) FactoryController.controller("humano"))::create);
            post("/registro/fisico", ((HumanoController) FactoryController.controller("humano"))::save);
            get("/fisico/contribuciones/{id}", ((HumanoController) FactoryController.controller("humano"))::consultarContribuciones);
            get("/persona/fisico/{id}", ((HumanoController) FactoryController.controller("humano"))::show);
        });

        Server.app().routes(()->{
            get("/incidentes",((FallaTecnicaController) FactoryController.controller("incidente"))::index);
            get("/registro/incidente",((FallaTecnicaController)FactoryController.controller("incidente"))::create);
            post("/registro/incidente",((FallaTecnicaController)FactoryController.controller("incidente"))::save);
            get("/incidentes/seguimiento", ((FallaTecnicaController)FactoryController.controller("incidente"))::verSeguimiento);
            get("/incidentes/{id}", ((FallaTecnicaController)FactoryController.controller("incidente"))::show);
        });

        Server.app().routes(()->{
            get("/deccosalud",((DeccoSaludController)FactoryController.controller("deccosalud"))::index);
        });

        Server.app().routes(()->{
            get("/tecnico/visita",((VisitaFallaTecnicaController)FactoryController.controller("visitaTecnica"))::create);
            post("/tecnico/visita",((VisitaFallaTecnicaController)FactoryController.controller("visitaTecnica"))::save);
        });

        Server.app().routes(()->{
            get("/registro/producto",((ProductoController)FactoryController.controller("producto"))::create);
            post("/registro/producto",((ProductoController)FactoryController.controller("producto"))::save);
            get("/productos",((ProductoController)FactoryController.controller("producto"))::index);
            get("/productos/{id}",((ProductoController)FactoryController.controller("producto"))::show);
        });

        Server.app().get("/canjeExitoso",((CanjeController)FactoryController.controller("canje"))::canjeExitoso);



       Server.app().routes(()->{
           get("/contribuciones",((ContribucionController)FactoryController.controller("Contribucion"))::index);
           get("/contribuciones/{id}",((ContribucionController)FactoryController.controller("Contribucion"))::create);
           post("/contribucionExitosa", ((ContribucionController)FactoryController.controller("Contribucion"))::save);
       });

        Server.app().routes(()->{
            // Pantallas de admins
            get("/registro/colaboradores", ((AdministradorController) FactoryController.controller("admin"))::getImportarColaborador);
            post("registro/colaboradores", ((AdministradorController) FactoryController.controller("admin"))::saveImportarColaborador);
            get("/index/administrador", ((AdministradorController) FactoryController.controller("admin"))::index);
            get("/persona/administrador/{id}", ((AdministradorController) FactoryController.controller("admin"))::show);
        });

        Server.app().routes(()->{
            get("/registro/tecnico",((TecnicoController)FactoryController.controller("tecnico"))::create);
            post("/registro/tecnico",((TecnicoController)FactoryController.controller("tecnico"))::save);
        });

        Server.app().routes(()->{
            get("/reportes",((ReporteController)FactoryController.controller("reporte"))::index);
            get("/reportes/{id}",((ReporteController)FactoryController.controller("reporte"))::create);
        });

        Server.app().routes(()->{
            post("/heladeras/{id}",((SuscripcionesController) FactoryController.controller("suscripciones"))::save);
        });

    }

}
