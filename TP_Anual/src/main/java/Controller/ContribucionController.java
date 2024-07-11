package Controller;

import Controller.Actores.Usuario;
import Models.Domain.FormasDeContribucion.FactoryContribucion;
import Models.Domain.FormasDeContribucion.FormaDeContribucion;
import Models.Domain.Personas.Colaborador;
import Models.Repository.RepoColaboradores;
import Service.Server.ICrudViewsHandler;

public class ContribucionController extends Controller implements ICrudViewsHandler {

    public ContribucionController(Usuario usuario) {
        this.usuario = usuario;
    }


    public void index(Object ... Context){

    }
    public void show(Object ... Context){

    }
    public void create(Object ... Context) {
        // SOLO PUEDE HACERLO NORMAL



    }

    public void save(Object ... Context){
        FactoryContribucion factoryContribucion = new FactoryContribucion((Colaborador) this.usuario);
        FormaDeContribucion nuevaContribucion = factoryContribucion.factoryMethod(Context);

        // Repo guardar
        // Context.redirect

    }
    public void edit(Object ... Context){

    }
    public void update(Object ... Context){

    }
    public void delete(Object ... Context){

    }

}
