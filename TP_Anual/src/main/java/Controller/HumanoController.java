package Controller;

import Controller.Actores.Rol;
import Models.Personas.Humano;

public class HumanoController extends Controller {

    public void create(Object ... Args) {
        this.usuario = new Humano( (String) Args[0],(String) Args[1]);
    }

    public void solicitudTarjeta(){
        this.checkUserRoleAndProceed(Rol.HUMANO);
        // SOLICITA TARJETA
    }

    public void edit() {

    }

    public void delete() {

    }
}
