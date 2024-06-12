package Controller;

import Controller.Actores.Rol;
import Models.Domain.Personas.Humano;
import Models.Repository.Dao;

public class HumanoController extends Controller {

    public void create(Object ... Args) {
        this.usuario = new Humano( (String) Args[0],(String) Args[1]);
    }

    public void solicitudTarjeta(){
        this.checkUserRoleAndProceed(Rol.HUMANO);
        // SOLICITA TARJETA
    }

    // POST
    public void generarCanje(Object ... Context){
        this.checkUserRoleAndProceed(Rol.HUMANO);
      //  usuario.realizarCanje(Context[0],Context[1]);
      //  Dao repositorio = new Dao();
      //  repositorio.modificar(usuario);
    }

    // GET
    public void verCanje(Object ... Context){
        this.checkUserRoleAndProceed(Rol.HUMANO);
        // DATOS DE CANJE
        // Javalin.mostrar(ruta, datos) -> []
    }


    public void edit() {

    }


    public void delete() {

    }
}
