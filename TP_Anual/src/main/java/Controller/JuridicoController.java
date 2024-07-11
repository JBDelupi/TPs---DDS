package Controller;

import Controller.Actores.TipoRol;
import Controller.Actores.Usuario;
import Models.Domain.Builder.UsuariosBuilder.JuridicoBuilder;
import Models.Domain.Personas.Juridico;
import Models.Domain.TipoJuridico;
import Models.Repository.RepoColaboradores;
import Service.Server.ICrudViewsHandler;


public class JuridicoController extends Controller implements ICrudViewsHandler {

    public JuridicoController(Usuario usuario) {
        this.usuario = usuario;
    }

    //  @POST
    public void save(Object ... Context) {

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

    public void index(Object ... Context){

    }
    public void show(Object ... Context){

    }

    //@GET
    public void create(Object ... Context){

    }
    public void edit(Object ... Context){

    }
    public void update(Object ... Context){

    }
    public void delete(Object ... Context){

    }


}
