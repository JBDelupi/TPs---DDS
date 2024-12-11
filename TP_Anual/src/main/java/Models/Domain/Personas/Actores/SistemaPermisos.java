package Models.Domain.Personas.Actores;

import Service.Server.exceptions.AccessDeniedException;

public class SistemaPermisos {
    public static void  check(Persona persona, TipoRol tipoRol,String accion){
        if(!persona.checkRol(tipoRol)) {

            throw new AccessDeniedException();
        }
        Rol rol = persona.getRol(tipoRol);
        if( !rol.getPermisos(persona).stream().anyMatch(f->f.equals(accion) )){

            throw new AccessDeniedException();
        }

    }
}
