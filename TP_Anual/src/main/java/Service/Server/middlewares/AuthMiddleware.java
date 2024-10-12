package Service.Server.middlewares;

import Controller.Actores.RolUsuario;
import Models.Domain.Personas.Actores.TipoRol;
import Service.Server.exceptions.AccessDeniedException;
import Service.Server.exceptions.UnauthorizedResponseException;
import io.javalin.config.JavalinConfig;
import io.javalin.http.Context;

public class AuthMiddleware {

    public static void apply(JavalinConfig config) {
        config.accessManager(((handler, context, routeRoles) -> {
            RolUsuario userRole = getUserRoleType(context);

            if(routeRoles.isEmpty() || routeRoles.contains(userRole)) {
                handler.handle(context);
            }
            else {
                throw new UnauthorizedResponseException();
            }
        }));
    }

    private static RolUsuario getUserRoleType(Context context) {
        return context.sessionAttribute("rolTipo") != null?
                RolUsuario.valueOf(context.sessionAttribute("rolTipo")) : null;
    }
}
