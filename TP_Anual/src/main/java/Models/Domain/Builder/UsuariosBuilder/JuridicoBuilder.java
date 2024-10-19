package Models.Domain.Builder.UsuariosBuilder;

import Controller.Actores.RolUsuario;
import Models.Domain.Personas.Actores.Juridico;
import Models.Domain.Personas.Actores.Rol;
import Models.Domain.Personas.DatosPersonales.Direccion;
import Models.Domain.Personas.Utilidades.TipoJuridico;
import Service.Validador.CredencialDeAcceso;

public class JuridicoBuilder {
    private Juridico juridico;

    public JuridicoBuilder() {
        this.juridico = new Juridico();
    }

    public JuridicoBuilder razonSocial(String razonSocial) {
        this.juridico.setRazonSocial(razonSocial);
        return this;
    }

    public JuridicoBuilder tipoJuridico(TipoJuridico tipoJuridico) {
        this.juridico.setTipoJuridico(tipoJuridico);
        return this;
    }

    public JuridicoBuilder correoElectronico(String correoElectronico) {
        this.juridico.setCorreElectronico(correoElectronico);
        this.juridico.setCodigoDeNotificacion(correoElectronico);
        return this;
    }

    public JuridicoBuilder sede(Direccion direccion) {
        this.juridico.setDireccion(direccion);
        return this;
    }


    public JuridicoBuilder rol(Rol rol){
        this.juridico.agregarRol(rol);
        return this;
    }

    public JuridicoBuilder credencialDeAcceso(CredencialDeAcceso credencialDeAcceso) {
        this.juridico.setCredencialDeAcceso(credencialDeAcceso);
        return this;
    }


    public Juridico construir(){
        juridico.setTipoUsuario(RolUsuario.JURIDICO);
        juridico.setCodigoDeNotificacion(juridico.getCorreElectronico());

        return this.juridico;
    }

}
