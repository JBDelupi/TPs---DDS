package Controller.Administrador;

import Controller.Controller;
import Service.DTO.HumanoDTO;
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
        context.render("Adnministrador/Index_Admin.hbs");
    }

    public void getImportarColaborador(Context context){

        context.render("Administrador/cargar-colaboradores.hbs");
    }

    public void saveImportarColaborador(Context context) throws CsvValidationException, IOException {
        UploadedFile file = context.uploadedFile("csvFileInput");
        String token = context.formParam("token");

        String filename = file.filename();
        String ruta = "C:\\Facultad 2024\\Back up 2024\\Diseño de sistemas\\TPs---DDS\\TP_Anual\\";

        Set<HumanoDTO> importadosCSV = ImportadorCSV.getInstance(ruta + filename, token).getColaboradoresDTO();

        Map<String, Object> model = new HashMap<>();

        model.put("colaboradores",importadosCSV);

        context.render("Administrador/cargar-colaboradores.hbs",model);
    }

    public void show(Context context){

        context.render("Administrador/perfilAdministrador.hbs");
    }

}
