package Controller;

import Controller.Actores.Rol;
import Models.Domain.Heladera;

public class HeladeraController extends Controller {

    public void create(Object... Args) {
        this.checkUserRoleAndProceed(Rol.JURIDICO);
        Heladera nuevaHeladera = new Heladera();

    }

    public void update(Object... Args) {

    }

}
