package Controller;

import Controller.Actores.Usuario;
import Models.Domain.FormasDeContribucion.Utilidades.FactoryContribucion;
import Models.Domain.FormasDeContribucion.Utilidades.FormaDeContribucion;
import Models.Domain.Personas.Actores.Colaborador;
import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

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
        
        // GUARDAR BASE DE DATOS



    }

    public void edit(Object ... Context){

    }
    public void update(Object ... Context){

    }
    public void delete(Object ... Context){

    }


    @Override
    public void index(Context context) {

    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) {

    }

    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {

    }


}
