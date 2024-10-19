package Models.Domain.Builder.UsuariosBuilder;

import Service.APIPuntos.AreaCobertura;
import Models.Domain.Personas.Actores.Tecnico;

public class TecnicoBuilder {
    private Tecnico tecnico;

    public TecnicoBuilder(){
        this.tecnico = new Tecnico();
    }


    public TecnicoBuilder cuil(String cuit){
        tecnico.setCuil(cuit);
        return this;
    }


    public TecnicoBuilder area(AreaCobertura areaCobertura){
        tecnico.setArea(areaCobertura);
        return this;
    }

    public Tecnico construir(){
        return this.tecnico;
    }

}
