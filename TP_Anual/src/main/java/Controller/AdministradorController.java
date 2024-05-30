package Controller;

import Controller.Actores.Rol;
import Models.Personas.Tecnico;

public class AdministradorController extends Controller{

    public void TecnicoCreate(Object ... Args) {
        this.checkUserRoleAndProceed(Rol.ADMINISTRADOR);
        new Tecnico();
    }


    public void importarColabodores(Object... Args) {
        //link
        //Importador(link)
        // Lista Humanos
        //[DNI, TIPO, NOMBRE, APELLIDO,.....]
        // [0,1 -> Directamente ni lo cargues]
        // -> Guardarlos en la base de datos
        // REEDIRGILO A UNA PAGINA QUE DIGA USUARIO CARGADO CON EXITOO
    }

    @Override
    public void create(Object... Args) {

    }
}
