package Controller;

import Models.Personas.Humano;
import Models.Personas.Rol;
import Models.Personas.Tecnico;

public class AdministradorController extends Controller{

    public void TecnicoCreate(Object ... Args) {
        this.checkUserRoleAndProceed(Rol.ADMINISTRADOR);
        new Tecnico();
    }

    @Override
    public void create(Object... Args) {

    }
}
