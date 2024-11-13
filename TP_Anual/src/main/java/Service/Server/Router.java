package Service.Server;

import Controller.*;
import Controller.Actores.RolUsuario;
import Controller.Administrador.AdministradorController;
import Controller.Administrador.HeladeraController;
import Controller.Administrador.TecnicoController;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;

public class Router {
    public static void init(){

        Server.app().get("/",context -> context.render("main/index.hbs"));
        Server.app().get("/contact",context -> context.render("main/Contacto.hbs"));
        Server.app().get("/team",context -> context.render("main/team.hbs"));
        Server.app().get("/about",context -> context.render("main/about.hbs"));
        Server.app().get("/registro",context -> context.render("sesion/registro.hbs"));
        Server.app().get("/asignar-rol",context -> context.render("asignarRol/asignar-rol.hbs"));
        Server.app().get("/asignar-rol/tecnico",context -> context.render("asignarRol/solicitud-tecnico.hbs"));
        Server.app().get("/asignar-rol/solicitudExitosa",context -> context.render("asignarRol/solicitud-enviada.hbs"));


        Server.app().get("registro/puntos",((PuntoCercanoController)FactoryController.controller("puntos"))::index,  RolUsuario.ADMINISTRADOR , RolUsuario.JURIDICO);
        Server.app().post("registro/puntos",((PuntoCercanoController)FactoryController.controller("puntos"))::cargarPuntos,  RolUsuario.ADMINISTRADOR , RolUsuario.JURIDICO);


        Server.app().routes(()->{
            post("/logout", ((LoginController)FactoryController.controller("login"))::manejarCierreSesion);
            get("/login", ((LoginController) FactoryController.controller("login"))::index);
            post("/login", ((LoginController) FactoryController.controller("login"))::manejarInicioSesion);
        });


        Server.app().routes(()->{
            get("/recuperar", ((RecuperarController) FactoryController.controller("recuperar"))::index); //para el olvide mi contrasena
        });

        Server.app().routes(()->{
            get("/index/juridico", ((JuridicoController) FactoryController.controller("juridico"))::index, RolUsuario.ADMINISTRADOR , RolUsuario.JURIDICO);
            get("/registro/juridico", ((JuridicoController) FactoryController.controller("juridico"))::create);
            post("/registro/juridico", ((JuridicoController) FactoryController.controller("juridico"))::save);
            get("/persona/juridico/{id}", ((JuridicoController) FactoryController.controller("juridico"))::show,  RolUsuario.ADMINISTRADOR , RolUsuario.JURIDICO);
        });

        Server.app().routes(()->{
            get("/registro/heladera",((HeladeraController) FactoryController.controller("heladeras"))::create, RolUsuario.ADMINISTRADOR);
            get("/heladeras",((HeladeraController) FactoryController.controller("heladeras"))::index);
            post("/heladeras/{id}/suscribir",((HeladeraController) FactoryController.controller("heladeras"))::edit);
            post("/heladeras/{id}/desuscribir",((HeladeraController) FactoryController.controller("heladeras"))::update);
            get("/heladeras/{id}",((HeladeraController) FactoryController.controller("heladeras"))::show);
            post("/registro/heladera",((HeladeraController) FactoryController.controller("heladeras"))::save);

        });

        Server.app().routes(()->{
            get("/index/fisico",((HumanoController) FactoryController.controller("humano"))::index, RolUsuario.FISICO , RolUsuario.ADMINISTRADOR );
            get("/registro/fisico", ((HumanoController) FactoryController.controller("humano"))::create);
            post("/persona/fisico/{id}", ((HumanoController) FactoryController.controller("humano"))::update, RolUsuario.FISICO , RolUsuario.ADMINISTRADOR);
            post("/registro/fisico", ((HumanoController) FactoryController.controller("humano"))::save);
            get("/persona/fisico/{id}", ((HumanoController) FactoryController.controller("humano"))::show, RolUsuario.FISICO , RolUsuario.ADMINISTRADOR );
        });

        Server.app().routes(()->{
            get("/incidentes",((FallaTecnicaController) FactoryController.controller("incidente"))::index, RolUsuario.ADMINISTRADOR, RolUsuario.FISICO);
            get("/registro/incidente",((FallaTecnicaController)FactoryController.controller("incidente"))::create, RolUsuario.FISICO , RolUsuario.ADMINISTRADOR);
            post("/registro/incidente",((FallaTecnicaController)FactoryController.controller("incidente"))::save );
            get("/incidentes/seguimiento", ((FallaTecnicaController)FactoryController.controller("incidente"))::verSeguimiento, RolUsuario.FISICO , RolUsuario.ADMINISTRADOR);
            get("/incidentes/{id}", ((FallaTecnicaController)FactoryController.controller("incidente"))::show, RolUsuario.FISICO , RolUsuario.ADMINISTRADOR);
        });

        Server.app().routes(()->{
            get("/deccosalud",((DeccoSaludController)FactoryController.controller("deccosalud"))::index);
        });

        Server.app().routes(()->{
            get("/tecnico/visita",((VisitaFallaTecnicaController)FactoryController.controller("visitaTecnica"))::create);
            post("/tecnico/visita",((VisitaFallaTecnicaController)FactoryController.controller("visitaTecnica"))::save);
        });

        Server.app().routes(()->{
            get("/productos",((ProductoController)FactoryController.controller("producto"))::index);
            get("/productos/{id}",((ProductoController)FactoryController.controller("producto"))::show);
            post("/canjeExitoso",((ProductoController)FactoryController.controller("producto"))::canjeExitoso);
            get("{rol}/{userId}/historialcanjes",((ProductoController)FactoryController.controller("producto"))::historialCanjes);
        });

       Server.app().routes(()->{
           get("{rol}/{userId}/contribuciones", ((ContribucionController) FactoryController.controller("Contribucion"))::consultarContribuciones);
           get("/contribuciones",((ContribucionController)FactoryController.controller("Contribucion"))::index);
           get("/contribuciones/{id}",((ContribucionController)FactoryController.controller("Contribucion"))::create);
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
            get("/registro/tecnico",((TecnicoController)FactoryController.controller("tecnico"))::create);
            post("/registro/tecnico",((TecnicoController)FactoryController.controller("tecnico"))::save);
            get("/index/registro/tecnico",((TecnicoController)FactoryController.controller("tecnico"))::index);
            get("/registro/rol/tecnico",((TecnicoController)FactoryController.controller("tecnico"))::update);
            post("/registro/rol/tecnico",((TecnicoController)FactoryController.controller("tecnico"))::edit);
        });

        Server.app().routes(()->{
            get("/reportes",((ReporteController)FactoryController.controller("reporte"))::index);
            get("/reportes/listadoReportes",((ReporteController)FactoryController.controller("reporte"))::show);
            post("/reportes/detalles",((ReporteController)FactoryController.controller("reporte"))::reporte);
        });


    }

}
