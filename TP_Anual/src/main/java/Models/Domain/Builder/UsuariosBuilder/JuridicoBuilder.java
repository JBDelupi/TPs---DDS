package Models.Domain.Builder.UsuariosBuilder;

import Models.Domain.Personas.Juridico;
import Models.Domain.TipoJuridico;

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

    public JuridicoBuilder correoElectronico(String correo){
        this.juridico.setCorreoElectronico(correo);
        return this;
    }


    public Juridico construir(){
        return this.juridico;
    }

}
