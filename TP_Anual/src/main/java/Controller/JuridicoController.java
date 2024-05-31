package Controller;

import Models.Direccion;
import Models.MedioDeNotificacion;
import Models.Personas.Humano;
import Models.Personas.Juridico;
import Models.TipoJuridico;

public class JuridicoController extends Controller {

    public void create(Object ... Args) {
        this.usuario = new Juridico( (String) Args[0], (TipoJuridico) Args[1], (Direccion) Args[2], (MedioDeNotificacion) Args[3] );
    }

    public void edit() {

    }

    public void delete() {

    }


}
