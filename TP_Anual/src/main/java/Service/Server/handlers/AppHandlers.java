package Service.Server.handlers;
import Service.Server.exceptions.UnauthorizedResponseException;
import io.javalin.Javalin;
import java.util.Arrays;

public class AppHandlers {
    private IHandler[] handlers = new IHandler[]{
            new AccessDeniedHandler(),
            new InvalidPasswordHandler(),
            new UnauthorizeHandler(),
            new NoSuchFieldExceptionHandler(),
            new PuntosInsuficientesHandler(),
            new HeladeraLlenaHandler(),
            new CapacidadHeladeraHandler(),
            new SinViandasHandler(),
            new UsuarioYaTieneRolHandler(),
            new UserAlreadyExistsHandler()
    };

    public static void applyHandlers(Javalin app) {
        Arrays.stream(new AppHandlers().handlers).toList().forEach(handler -> handler.setHandle(app));
    }
}
