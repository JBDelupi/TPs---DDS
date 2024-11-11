package Models.Domain.Builder.UsuariosBuilder;


import Controller.Actores.RolUsuario;
import Models.Domain.Personas.Actores.Rol;
import Models.Domain.Personas.Actores.Fisico;
import Models.Domain.Personas.DatosPersonales.Direccion;
import Models.Domain.Personas.DatosPersonales.TipoDeDocumento;
import Service.Notificacion.Correo.CorreoAdapter;
import Service.Validador.CredencialDeAcceso;

import java.time.LocalDate;

public class FisicoBuilder {
    private Fisico fisico;

    public FisicoBuilder(){
        this.fisico = new Fisico();
    }

    public FisicoBuilder nombre(String nombre){
        this.fisico.setNombre(nombre);
        return this;
    }

    public FisicoBuilder apellido(String apellido){
        this.fisico.setApellido(apellido);
        return this;
    }

    public FisicoBuilder correoElectronico(String correoElectronico){
        this.fisico.setCorreElectronico(correoElectronico);
        return this;
    }

    public FisicoBuilder fechaNacimiento(LocalDate fechaNacimiento){
        this.fisico.setFechaNacimiento(fechaNacimiento);
        return this;
    }

    public FisicoBuilder numeroDocumento(String documento){
        this.fisico.setNumeroDocumento(documento);
        return this;
    }

    public FisicoBuilder tipoDocumento(TipoDeDocumento documento){
        this.fisico.setTipoDeDocumento(documento);
        return this;
    }

    public FisicoBuilder credencialDeAcceso(CredencialDeAcceso credencialDeAcceso){
        this.fisico.setCredencialDeAcceso(credencialDeAcceso);
        return this;
    }

    public FisicoBuilder rol(Rol rol){
        this.fisico.agregarRol(rol);
        return this;
    }

    public FisicoBuilder direccion(Direccion direccion){
        this.fisico.setDireccion(direccion);
        return this;
    }

    public Fisico construir(){
        fisico.setTipoUsuario(RolUsuario.FISICO);
        fisico.setMedioDeNotificacion(new CorreoAdapter());
        fisico.setCodigoDeNotificacion(fisico.getCorreElectronico());
        return this.fisico;
    }

}
