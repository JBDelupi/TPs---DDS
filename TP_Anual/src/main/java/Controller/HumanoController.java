package Controller;

import Models.Personas.Humano;
import Models.Personas.Rol;

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
