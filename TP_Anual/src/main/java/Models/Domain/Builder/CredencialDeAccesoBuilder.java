package Models.Domain.Builder;

import Service.Validador.CredencialDeAcceso;

public class CredencialDeAccesoBuilder {
    private CredencialDeAcceso credencialDeAcceso;

    public CredencialDeAccesoBuilder() {
        this.credencialDeAcceso = new CredencialDeAcceso();
    }

    public CredencialDeAccesoBuilder nombreUsuario(String nombre){
        this.credencialDeAcceso.setNombreUsuario(nombre);
        return this;
    }

    public CredencialDeAccesoBuilder contrasenia(String contrasenia){
        this.credencialDeAcceso.setContrasenia(contrasenia);
        return this;
    }

    public CredencialDeAcceso construir(){
        return this.credencialDeAcceso;
    }
}
