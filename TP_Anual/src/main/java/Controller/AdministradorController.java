package Controller;

import Controller.Actores.Rol;
import Models.Domain.Builder.UsuariosBuilder.TecnicoBuilder;
import Models.Domain.Personas.AreaCobertura;
import Models.Domain.Personas.Tecnico;
import Models.Domain.TipoDeDocumento;

public class AdministradorController extends Controller{

    public Tecnico tecnicoCreate(Object ... Context) {
        this.checkUserRoleAndProceed(Rol.ADMINISTRADOR);
        String nombre = (String) Context[0];
        String apellido = (String) Context[1];
        TipoDeDocumento tipo = (TipoDeDocumento) Context[2];
        String numeroDocumento = (String) Context[3];
        String cuil = (String) Context[4];
        AreaCobertura areaCobertura = (AreaCobertura) Context[5];

        TecnicoBuilder builder = new TecnicoBuilder();
        return builder
                .nombre(nombre)
                .apellido(apellido)
                .tipoDeDocumento(tipo)
                .numeroDeDocumento(numeroDocumento)
                .cuil(cuil)
                .area(areaCobertura)
                .contruir();

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
