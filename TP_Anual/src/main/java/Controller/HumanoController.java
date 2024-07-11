package Controller;

import Controller.Actores.TipoRol;
import Models.Domain.Builder.UsuariosBuilder.HumanoBuilder;
import Models.Domain.Personas.Humano;
import Models.Domain.TipoDeDocumento;
import Models.Repository.RepoColaboradores;

import java.time.LocalDate;

public class HumanoController extends Controller {

    public void create(Object ... Args) {

        String nombre = (String) Args[0];
        String apellido = (String) Args[1];
        LocalDate fechaNacimiento = (LocalDate) Args[2];
        String correo = (String) Args[3];
        String nroDocumento = (String) Args[4];
        TipoDeDocumento tipoDeDocumento = (TipoDeDocumento) Args[5];

        HumanoBuilder humanoBuilder = new HumanoBuilder();
        Humano humano = humanoBuilder
                .nombre(nombre)
                .apellido(apellido)
                .fechaNacimiento(fechaNacimiento)
                .correoElectronico(correo)
                .numeroDocumento(nroDocumento)
                .tipoDocumento(tipoDeDocumento)
                .construir();

        RepoColaboradores.getInstance().agregarColaborador(humano);
    }

    public void solicitudTarjeta(){
        this.checkUserRoleAndProceed(TipoRol.HUMANO);
        // SOLICITA TARJETA
    }

    // POST
    public void generarCanje(Object ... Context){
        this.checkUserRoleAndProceed(TipoRol.HUMANO);

    }

    // GET
    public void verCanje(Object ... Context){
        this.checkUserRoleAndProceed(TipoRol.HUMANO);

    }


    public void edit() {

    }


    public void delete() {

    }
}
