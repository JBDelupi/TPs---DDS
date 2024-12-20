package Models.Domain.Personas.Actores;

import Service.APIPuntos.AreaCobertura;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter @Getter
@Entity
@DiscriminatorValue("Tecnico")
public class Tecnico extends Rol {

    @Column(name ="cuil")
    private String cuil;

    @Embedded
    private AreaCobertura area;

    public Tecnico(){
        this.tipo = TipoRol.TECNICO;
    }

    public Tecnico(String cuil, AreaCobertura area){
        this.tipo = TipoRol.TECNICO;
        this.cuil = cuil;
        this.area = area;
    }


    public List<String> getPermisos(Persona persona) {
        List<String> permisos = new ArrayList<>(List.of("incidentes"));

        return permisos;
    }
}
