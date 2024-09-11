package Controller;

import Controller.Actores.TipoRol;
import Controller.Actores.Usuario;
import io.javalin.http.Context;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public abstract class Controller {
    Usuario usuario;

}
