package Controller.Administrador;

import Controller.Controller;
import Models.Domain.Builder.UsuariosBuilder.TecnicoBuilder;
import Service.APIPuntos.AreaCobertura;
import Models.Domain.Personas.Actores.Tecnico;
import Models.Domain.Personas.DatosPersonales.TipoDeDocumento;
import Service.Server.ICrudViewsHandler;

public class TecnicoController extends Controller implements ICrudViewsHandler {

    public void create(Object ... Context) {

        String nombre = (String) Context[0];
        String apellido = (String) Context[1];
        TipoDeDocumento tipo = (TipoDeDocumento) Context[2];
        String numeroDocumento = (String) Context[3];
        String cuil = (String) Context[4];
        AreaCobertura areaCobertura = (AreaCobertura) Context[5];

        TecnicoBuilder builder = new TecnicoBuilder();
        Tecnico tecnico = builder
                .nombre(nombre)
                .apellido(apellido)
                .tipoDeDocumento(tipo)
                .numeroDeDocumento(numeroDocumento)
                .cuil(cuil)
                .area(areaCobertura)
                .construir();

    }

    public void index(Object ... Context){

    }
    public void show(Object ... Context){

    }

    public void save(Object ... Context){

    }
    public void edit(Object ... Context){

    }
    public void update(Object ... Context){

    }
    public void delete(Object ... Context){

    }

}
