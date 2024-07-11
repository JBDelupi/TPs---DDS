package Controller;

import Controller.Actores.TipoRol;
import Models.Domain.Heladera;

public class HeladeraController extends Controller {

    public void create(Object... Args) {
        this.checkUserRoleAndProceed(TipoRol.JURIDICO);
        Heladera nuevaHeladera = new Heladera();

    }

    public void update(Object... Args) {

    }

}
