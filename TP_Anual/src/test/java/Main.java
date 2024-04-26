import Controller.Controller;
import Controller.HumanoController;
import Controller.DonacionController;
import Controller.PersonaVulnerableController;

import Models.Personas.Administrador;
import Models.Personas.Colaborador;
import Models.Personas.Usuario;


public class Main {
    public static void main(String[] args) {
        Controller usuario = new HumanoController();
        usuario.create("lucas","iturrioz");
        Controller donacion = new DonacionController( usuario.getUsuario() );

        Usuario test = new Administrador();

        Controller personaVulnerable = new PersonaVulnerableController( usuario.getUsuario() );

        personaVulnerable.create("lucas");
    }

}
