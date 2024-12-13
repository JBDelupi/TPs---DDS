package Service.Server;

import Controller.*;
import Controller.Actores.RolUsuario;
import Controller.AdministradorController;
import Controller.HeladeraController;
import Controller.TecnicoController;


import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;

public class Router {
    public static void init(){
        //Server.app().get("/",context -> context.render("main/index.hbs")); // esto ya esta abajo en controller heladera
        Server.app().get("/contact",context -> context.render("main/Contacto.hbs"));
        Server.app().get("/team",context -> context.render("main/team.hbs"));
        Server.app().get("/about",context -> context.render("main/about.hbs"));
        Server.app().get("/registro",context -> context.render("Sesion/registro.hbs"));
        Server.app().get("/recuperar",context -> context.render("Sesion/recuperar.hbs"));


        Server.app().get("/asignar-rol/tecnico",context -> context.render("Asignar-rol/solicitud-tecnico.hbs"), RolUsuario.ADMINISTRADOR , RolUsuario.JURIDICO,RolUsuario.FISICO);
        Server.app().get("/asignar-rol/solicitudExitosa",context -> context.render("Asignar-rol/solicitud-enviada.hbs"), RolUsuario.ADMINISTRADOR , RolUsuario.JURIDICO,RolUsuario.FISICO);




        Server.app().get("registro/puntos",((PuntoCercanoController)FactoryController.controller("puntos"))::index,  RolUsuario.ADMINISTRADOR , RolUsuario.JURIDICO);
        Server.app().post("registro/puntos",((PuntoCercanoController)FactoryController.controller("puntos"))::cargarPuntos,  RolUsuario.ADMINISTRADOR , RolUsuario.JURIDICO);


        Server.app().routes(()->{
            post("/logout", ((LoginController)FactoryController.controller("login"))::manejarCierreSesion);
            get("/login", ((LoginController) FactoryController.controller("login"))::index);
            post("/login", ((LoginController) FactoryController.controller("login"))::manejarInicioSesion);
        });


        Server.app().routes(()->{
            get("/index/juridico", ((JuridicoController) FactoryController.controller("juridico"))::index, RolUsuario.ADMINISTRADOR , RolUsuario.JURIDICO);
            get("/registro/juridico", ((JuridicoController) FactoryController.controller("juridico"))::create);
            post("/registro/juridico", ((JuridicoController) FactoryController.controller("juridico"))::save);
            get("/persona/juridico/{id}", ((JuridicoController) FactoryController.controller("juridico"))::show,  RolUsuario.ADMINISTRADOR , RolUsuario.JURIDICO);
            post("/registro/darse-de-baja", ((JuridicoController) FactoryController.controller("juridico"))::delete);
        });

        Server.app().routes(()->{
            get("/registro/heladera",((HeladeraController) FactoryController.controller("heladeras"))::create, RolUsuario.ADMINISTRADOR, RolUsuario.JURIDICO);
            get("/heladeras",((HeladeraController) FactoryController.controller("heladeras"))::index,RolUsuario.ADMINISTRADOR , RolUsuario.JURIDICO,RolUsuario.FISICO);
            post("/heladeras/{id}/suscribir",((HeladeraController) FactoryController.controller("heladeras"))::edit);
            post("/heladeras/{id}/desuscribir",((HeladeraController) FactoryController.controller("heladeras"))::update);
            get("/heladeras/{id}",((HeladeraController) FactoryController.controller("heladeras"))::show,RolUsuario.ADMINISTRADOR , RolUsuario.JURIDICO,RolUsuario.FISICO);
            post("/registro/heladera",((HeladeraController) FactoryController.controller("heladeras"))::save);
            get("/juridico/{id}/mis-heladeras", ((HeladeraController) FactoryController.controller("heladeras"))::mostrarMisHeladeras, RolUsuario.JURIDICO,RolUsuario.ADMINISTRADOR);
            get("/heladeras/{id}/estado", ((HeladeraController) FactoryController.controller("heladeras"))::mostrarEstadoHeladera,RolUsuario.JURIDICO,RolUsuario.ADMINISTRADOR);
            post("/heladeras/{id}/estado", ((HeladeraController) FactoryController.controller("heladeras"))::cambiarEstadoHeladera);
            get("/", ((HeladeraController) FactoryController.controller("heladeras"))::mostrarCantidadHeladerasActivas);
            post("/heladeras/{id}/quitar-alerta", ((HeladeraController) FactoryController.controller("heladeras"))::quitarAlerta);
        });

        Server.app().routes(()->{
            get("/index/fisico",((HumanoController) FactoryController.controller("humano"))::index, RolUsuario.FISICO , RolUsuario.ADMINISTRADOR );
            get("/registro/fisico", ((HumanoController) FactoryController.controller("humano"))::create);
            post("/persona/fisico/{id}", ((HumanoController) FactoryController.controller("humano"))::update, RolUsuario.FISICO , RolUsuario.ADMINISTRADOR);
            post("/registro/fisico", ((HumanoController) FactoryController.controller("humano"))::save);
            post("/registro/fisico/{id}/darse-de-baja", ((HumanoController) FactoryController.controller("humano"))::delete);
            get("/persona/fisico/{id}", ((HumanoController) FactoryController.controller("humano"))::show, RolUsuario.FISICO , RolUsuario.ADMINISTRADOR );
            get("/asignar-rol", ((HumanoController) FactoryController.controller("humano")):: asignarRol);
        });

        Server.app().routes(()->{
            get("/incidentes",((FallaTecnicaController) FactoryController.controller("incidente"))::index, RolUsuario.ADMINISTRADOR, RolUsuario.FISICO, RolUsuario.JURIDICO);
            get("/registro/incidente",((FallaTecnicaController)FactoryController.controller("incidente"))::create, RolUsuario.FISICO , RolUsuario.ADMINISTRADOR);
            post("/registro/incidente",((FallaTecnicaController)FactoryController.controller("incidente"))::save );
            get("/incidentes/seguimiento", ((FallaTecnicaController)FactoryController.controller("incidente"))::verSeguimiento, RolUsuario.FISICO , RolUsuario.ADMINISTRADOR);
            get("/incidentes/{id}", ((FallaTecnicaController)FactoryController.controller("incidente"))::show, RolUsuario.FISICO , RolUsuario.ADMINISTRADOR, RolUsuario.JURIDICO);
        });

        Server.app().routes(()->{
            get("/deccosalud",((DeccoSaludController)FactoryController.controller("deccosalud"))::index,RolUsuario.ADMINISTRADOR , RolUsuario.JURIDICO,RolUsuario.FISICO);
            post("/deccosalud/detalles",((DeccoSaludController)FactoryController.controller("deccosalud"))::mostrarReporte,RolUsuario.ADMINISTRADOR , RolUsuario.JURIDICO,RolUsuario.FISICO);
        });

        Server.app().routes(()->{
            get("/tecnico/visita",((FallaTecnicaController)FactoryController.controller("incidente"))::edit,RolUsuario.ADMINISTRADOR , RolUsuario.JURIDICO,RolUsuario.FISICO);
            post("/tecnico/visita",((FallaTecnicaController)FactoryController.controller("incidente"))::update);
        });

        Server.app().routes(()->{
            get("/productos",((ProductoController)FactoryController.controller("producto"))::index,RolUsuario.ADMINISTRADOR , RolUsuario.JURIDICO,RolUsuario.FISICO);
            get("/productos/{id}",((ProductoController)FactoryController.controller("producto"))::show,RolUsuario.ADMINISTRADOR , RolUsuario.JURIDICO,RolUsuario.FISICO);
            post("/canjeExitoso",((ProductoController)FactoryController.controller("producto"))::canjeExitoso);
            get("{rol}/{userId}/historialcanjes",((ProductoController)FactoryController.controller("producto"))::historialCanjes,RolUsuario.ADMINISTRADOR , RolUsuario.JURIDICO,RolUsuario.FISICO);
        });

       Server.app().routes(()->{
           get("{rol}/{userId}/contribuciones", ((ContribucionController) FactoryController.controller("Contribucion"))::consultarContribuciones,RolUsuario.ADMINISTRADOR , RolUsuario.JURIDICO,RolUsuario.FISICO);
           get("/contribuciones",((ContribucionController)FactoryController.controller("Contribucion"))::index,RolUsuario.ADMINISTRADOR , RolUsuario.JURIDICO,RolUsuario.FISICO);
           get("/contribuciones/{id}",((ContribucionController)FactoryController.controller("Contribucion"))::create,RolUsuario.ADMINISTRADOR , RolUsuario.JURIDICO,RolUsuario.FISICO);
           post("/contribucionExitosa", ((ContribucionController)FactoryController.controller("Contribucion"))::save);
       });

        Server.app().routes(()->{
            // Pantallas de admins
            get("/registro/colaboradores", ((AdministradorController) FactoryController.controller("admin"))::getImportarColaborador, RolUsuario.ADMINISTRADOR);
            post("registro/colaboradores", ((AdministradorController) FactoryController.controller("admin"))::saveImportarColaborador);
            get("/index/administrador", ((AdministradorController) FactoryController.controller("admin"))::index, RolUsuario.ADMINISTRADOR);
            get("/persona/administrador/{id}", ((AdministradorController) FactoryController.controller("admin"))::show, RolUsuario.ADMINISTRADOR);
        });

        Server.app().routes(()->{
            get("/registro/tecnico",((TecnicoController)FactoryController.controller("tecnico"))::create,RolUsuario.ADMINISTRADOR);
            post("/registro/tecnico",((TecnicoController)FactoryController.controller("tecnico"))::save);
            get("/index/registro/tecnico",((TecnicoController)FactoryController.controller("tecnico"))::index,RolUsuario.ADMINISTRADOR);
            get("/registro/rol/tecnico",((TecnicoController)FactoryController.controller("tecnico"))::update,RolUsuario.ADMINISTRADOR);
            post("/registro/rol/tecnico",((TecnicoController)FactoryController.controller("tecnico"))::edit);
        });

        Server.app().routes(()->{
            get("/reportes",((ReporteController)FactoryController.controller("reporte"))::index,RolUsuario.ADMINISTRADOR , RolUsuario.JURIDICO,RolUsuario.FISICO);
            get("/reportes/listadoReportes",((ReporteController)FactoryController.controller("reporte"))::show,RolUsuario.ADMINISTRADOR , RolUsuario.JURIDICO,RolUsuario.FISICO);
            post("/reportes/detalles",((ReporteController)FactoryController.controller("reporte"))::reporte);
            get("/reportes/manual",((ReporteController)FactoryController.controller("reporte"))::generarManualmente,RolUsuario.ADMINISTRADOR);
        });


        Server.app().routes(() -> {
            get("/sso/login", ((LoginSSOController)FactoryController.controller("loginSSO"))::redirectToSSO);
           get("/oauth/callback", ((LoginSSOController)FactoryController.controller("loginSSO"))::handleCallback);
        });


    }

}
