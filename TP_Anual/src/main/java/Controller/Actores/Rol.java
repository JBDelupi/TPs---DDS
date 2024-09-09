package Controller.Actores;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Rol {

    private TipoRol tipo;


    private Set<Permiso> permisos;

    public Rol(TipoRol rol) {
        this.permisos = new HashSet<>();
        this.tipo = rol;
    }

    public void agregarPermisos(Permiso ... permisos) {
        Collections.addAll(this.permisos, permisos);
    }

    public boolean tenesPermiso(Permiso permiso) {
        return this.permisos.contains(permiso);
    }

    public boolean tenesPermiso(String nombreInterno) {
        return this.permisos.stream().anyMatch(p -> p.coincideConNombreInterno(nombreInterno));
    }
}
