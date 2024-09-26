package Service.Server.middlewares;

import Models.Domain.Personas.Actores.TipoRol;
import Service.Server.exceptions.AccessDeniedException;
import io.javalin.config.JavalinConfig;
import io.javalin.http.Context;

public class AuthMiddleware {

    public static void apply(JavalinConfig config) {
        config.accessManager(((handler, context, routeRoles) -> {
            TipoRol userRole = getUserRoleType(context);

            if(routeRoles.size() == 0 || routeRoles.contains(userRole)) {
                handler.handle(context);
            }
            else {
                throw new AccessDeniedException();
            }
        }));
    }

    private static TipoRol getUserRoleType(Context context) {
        return context.sessionAttribute("tipo_rol") != null?
                TipoRol.valueOf(context.sessionAttribute("tipo_rol")) : null;
    }
}
