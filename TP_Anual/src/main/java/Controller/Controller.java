package Controller;

import Models.Domain.Personas.Actores.Persona;
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
    Persona usuario;

    public void estaLogueado(Context context){
        if (context.sessionAttribute("usuario") == null) {
            throw new AccessDeniedException();
        }
    }

    public Map<String, Object> basicModel(Context context){
        String id = context.sessionAttribute("idPersona");
        this.usuario =  PseudoBaseDatosUsuario.getInstance().getId(id);

        Map<String, Object> model = new HashMap<>();
        model.put("rol", usuario.getTipoUsuario().toString().toLowerCase());
        model.put("id", id);
        return model;
    }


}
