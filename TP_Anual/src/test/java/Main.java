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

         Usuario test = new Administrador();

        Controller personaVulnerable = new PersonaVulnerableController( test );

        personaVulnerable.create("lucas");

    }

}
