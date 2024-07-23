package Models.Domain.Builder.UsuariosBuilder;


import Models.Domain.Personas.Actores.Humano;
import Models.Domain.Personas.DatosPersonales.TipoDeDocumento;

import java.time.LocalDate;

public class HumanoBuilder {
    private Humano humano;

    public HumanoBuilder(){
        this.humano = new Humano();
    }

    public HumanoBuilder nombre(String nombre){
        this.humano.setNombre(nombre);
        return this;
    }

    public HumanoBuilder apellido(String apellido){
        this.humano.setApellido(apellido);
        return this;
    }

    public HumanoBuilder fechaNacimiento(LocalDate fechaNacimiento){
        this.humano.setFechaNacimiento(fechaNacimiento);
        return this;
    }

    public HumanoBuilder correoElectronico(String correo){
        this.humano.setCorreoElectronico(correo);
        return this;
    }

    public HumanoBuilder numeroDocumento(String documento){
        this.humano.setNumeroDocumento(documento);
        return this;
    }

    public HumanoBuilder tipoDocumento(TipoDeDocumento documento){
        this.humano.setTipoDeDocumento(documento);
        return this;
    }


    public Humano construir(){
        if(humano.getFechaNacimiento() == null){
        }

        return this.humano;
    }

}
