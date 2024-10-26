package Service.Server;

import Controller.Actores.RolUsuario;
import Models.Domain.Personas.Actores.Fisico;
import Models.Domain.Personas.DatosPersonales.TipoDeDocumento;
import Models.Repository.Dao;
import Models.Repository.EntityManager.EntityManagerHelper;
import Models.Repository.RepoPersona;
import Service.Validador.CredencialDeAcceso;

public class App {
    public static void main(String[] args) {
        Server.init();

       /* Fisico admin = new Fisico();
        admin.setNombre("Decco");
        admin.setApellido("Colaboraciones");
        admin.setNumeroDocumento("25102024");
        admin.setTipoDeDocumento(TipoDeDocumento.DNI);
        admin.setCredencialDeAcceso(new CredencialDeAcceso("admin","admin"));
        admin.setTipoUsuario(RolUsuario.ADMINISTRADOR);
        Dao repo = new RepoPersona(Fisico.class);
        repo.agregar(admin); */
       

    }
}
