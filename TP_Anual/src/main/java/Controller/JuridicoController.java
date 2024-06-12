package Controller;

import Controller.Actores.Rol;
import Models.Domain.Builder.JuridicoBuilder;
import Models.Domain.Personas.Juridico;
import Models.Domain.TipoJuridico;
import Models.Repository.Dao;
import Models.Repository.RepoColaboradores;


public class JuridicoController extends Controller {

    public void create(Object ... Context) {

        String razonSocial = (String) Context[0];
        TipoJuridico tipoJuridico = (TipoJuridico) Context[1];
        String correo = (String) Context[2];

        JuridicoBuilder juridicoBuilder = new JuridicoBuilder();
        Juridico juridico = juridicoBuilder
                .razonSocial(razonSocial)
                .tipoJuridico(tipoJuridico)
                .correoElectronico(correo)
                .construir();


        RepoColaboradores.getInstance().agregarColaborador(juridico);

    }

    public void edit() {

    }

    public void generarCanje(Object ... Context){
        this.checkUserRoleAndProceed(Rol.JURIDICO);

    }

    // GET
    public void verCanje(Object ... Context){
        this.checkUserRoleAndProceed(Rol.JURIDICO);

    }


}
