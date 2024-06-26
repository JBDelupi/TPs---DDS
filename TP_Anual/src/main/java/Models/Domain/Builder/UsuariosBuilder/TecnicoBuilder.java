package Models.Domain.Builder.UsuariosBuilder;

import Models.Domain.Personas.AreaCobertura;
import Models.Domain.Personas.Tecnico;
import Models.Domain.TipoDeDocumento;

public class TecnicoBuilder {
    private Tecnico tecnico;

    public TecnicoBuilder(){
        this.tecnico = new Tecnico();
    }

    public TecnicoBuilder nombre(String nombre){
        tecnico.setNombre(nombre);
        return this;
    }

    public TecnicoBuilder apellido(String apellido){
        tecnico.setApellido(apellido);
        return this;
    }

    public TecnicoBuilder cuil(String cuil){
        tecnico.setCuil(cuil);
        return this;
    }

    public TecnicoBuilder medioDeContacto(String medioDeContacto){
        tecnico.setMedioDeContacto(medioDeContacto);
        return this;
    }

    public TecnicoBuilder area(AreaCobertura areaCobertura){
        tecnico.setArea(areaCobertura);
        return this;
    }

    public TecnicoBuilder tipoDeDocumento(TipoDeDocumento tipoDeDocumento){
        tecnico.setTipoDocumento(tipoDeDocumento);
        return this;
    }

    public TecnicoBuilder numeroDeDocumento(String numeroDeDocumento){
        tecnico.setNroDocumento(numeroDeDocumento);
        return this;
    }

    public Tecnico contruir(){
        return this.tecnico;
    }

}
/*
*
* private String nombre;
    private String apellido;
    private TipoDeDocumento tipoDocumento;
    private String nroDocumento;
    private String cuil;
    private String medioDeContacto; // ver si son varios o uno solo
    private AreaCobertura area;
*
* */