package Controller;

import Models.Domain.Direccion;
import Models.Domain.MedioDeNotificacion;
import Models.Domain.Personas.Juridico;
import Models.Domain.TipoJuridico;

public class JuridicoController extends Controller {

    public void create(Object ... Args) {
        this.usuario = new Juridico( (String) Args[0], (TipoJuridico) Args[1], (Direccion) Args[2], (MedioDeNotificacion) Args[3] );
    }

    public void edit() {

    }

    public void delete() {

    }


}
