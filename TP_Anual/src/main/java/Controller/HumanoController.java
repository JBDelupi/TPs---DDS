package Controller;

import Controller.Actores.TipoRol;
import Controller.Actores.Usuario;
import Models.Domain.Builder.UsuariosBuilder.HumanoBuilder;
import Models.Domain.Personas.Humano;
import Models.Domain.TipoDeDocumento;
import Models.Repository.RepoColaboradores;
import Service.Server.ICrudViewsHandler;

import java.time.LocalDate;

public class HumanoController extends Controller implements ICrudViewsHandler {

    public HumanoController(Usuario usuario) {
        this.usuario = usuario;
    }



    public void save(Object ... Args) {

        String nombre = (String) Args[0];
        String apellido = (String) Args[1];
        LocalDate fechaNacimiento = (LocalDate) Args[2];
        String correo = (String) Args[3];
        String nroDocumento = (String) Args[4];
        TipoDeDocumento tipoDeDocumento = (TipoDeDocumento) Args[5];

        HumanoBuilder humanoBuilder = new HumanoBuilder();
        Humano humano = humanoBuilder
                .nombre(nombre)
                .apellido(apellido)
                .fechaNacimiento(fechaNacimiento)
                .correoElectronico(correo)
                .numeroDocumento(nroDocumento)
                .tipoDocumento(tipoDeDocumento)
                .construir();

        RepoColaboradores.getInstance().agregarColaborador(humano);
    }

    public void index(Object ... Context){

    }
    public void show(Object ... Context){

    }

    public void create(Object ... Context){

    }
    public void edit(Object ... Context){

    }
    public void update(Object ... Context){

    }
    public void delete(Object ... Context){

    }


}
