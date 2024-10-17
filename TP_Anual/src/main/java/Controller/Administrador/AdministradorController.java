package Controller.Administrador;

import Controller.Controller;
import Models.Domain.Personas.Actores.Fisico;
import Models.Repository.PseudoBaseDatosUsuario;
import Service.ImportadorCSV.DTO.FisicoDTO;
import Service.ImportadorCSV.ImportadorCSV;
import com.opencsv.exceptions.CsvValidationException;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AdministradorController  extends Controller {



    public void index(Context context) {
        this.estaLogueado(context);

        context.render("Administrador/index_Admin.hbs", this.basicModel(context));
    }

    public void getImportarColaborador(Context context){
        this.estaLogueado(context);

        context.render("Administrador/cargar-colaboradores.hbs",this.basicModel(context));
    }

    public void saveImportarColaborador(Context context) throws CsvValidationException, IOException {
        UploadedFile file = context.uploadedFile("csvFileInput");
        String token = context.formParam("token");

        String filename = file.filename();
       // String ruta = "C:\\Facultad 2024\\Back up 2024\\Diseño de sistemas\\TPs---DDS\\TP_Anual\\";

        Set<FisicoDTO> importadosCSV = ImportadorCSV.getInstance( filename, token).getColaboradoresDTO();

        Map<String, Object> model = new HashMap<>();

        model.put("colaboradores",importadosCSV);

        context.render("Administrador/cargar-colaboradores.hbs",model);
    }

    public void show(Context context){
        this.estaLogueado(context);

        String id = context.pathParam("id");
        Fisico usuario = (Fisico) PseudoBaseDatosUsuario.getInstance().getId(id);
        Map<String, Object> model = this.basicModel(context);
        model.put("usuario",usuario);

        context.render("Administrador/perfilAdministrador.hbs",model);
    }


}
