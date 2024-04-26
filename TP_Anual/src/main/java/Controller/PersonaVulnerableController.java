package Controller;

import Models.Direccion;
import Models.MedioDeNotificacion;
import Models.Personas.*;
import Models.TipoJuridico;

public class PersonaVulnerableController extends Controller {

    public PersonaVulnerableController(Usuario colaborador){
        this.usuario = colaborador;
    }


    public void create(Object ... Args) {
        this.checkUserRoleAndProceed( Rol.ADMINISTRADOR );

        PersonaVulnerable personaVulnerable = new PersonaVulnerable();
        personaVulnerable.setNombre((String) Args[0]);

        System.out.println("Nueva persona incorporada " + personaVulnerable.getNombre() );
    }


}
