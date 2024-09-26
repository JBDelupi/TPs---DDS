package Controller;

import Models.Repository.PseudoBaseDatosUsuario;
import Service.Server.exceptions.AccessDeniedException;
import io.javalin.http.Context;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public abstract class Controller {
    Usuario usuario;

    public void estaLogueado(Context context){
        if (context.sessionAttribute("usuario") == null) {
            throw new AccessDeniedException();
        }
    }

    public Map<String, Object> basicModel(Context context){
        String id = context.sessionAttribute("idPersona");
        this.usuario =  PseudoBaseDatosUsuario.getInstance().getId(id);

        Map<String, Object> model = new HashMap<>();
        model.put("rol", usuario.getRol().getTipo().toString().toLowerCase());
        model.put("id", id);
        return model;
    }


}
