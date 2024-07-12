package Controller;

import Controller.Controller;
import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

public class HeladeraController extends Controller implements ICrudViewsHandler {

    public void index(Object ... Context){

    }

    public void index(Context context){
        context.render("fridge-map.hbs");
    }
    public void show(Object ... Context){

    }
    public void create(Object ... Context) {

    }

    public void save(Object ... Context){

    }
    public void edit(Object ... Context){

    }
    public void update(Object ... Context){

    }
    public void delete(Object ... Context){

    }

}
