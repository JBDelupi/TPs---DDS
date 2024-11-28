package Controller;

import Controller.Actores.RolUsuario;
import Models.Domain.Personas.Actores.TipoRol;
import Models.Repository.RepoPersona;
import Service.ImportadorCSV.DTO.FisicoDTO;
import Service.ImportadorCSV.ImportadorCSV;
import Service.ImportadorCSV.Mappers.GeneradorFisico;
import com.opencsv.exceptions.CsvValidationException;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AdministradorController  extends Controller {
    private final RepoPersona repo;

    public AdministradorController(RepoPersona repo) {
        this.repo = repo;
    }


    public void index(Context context) {
        this.estaLogueado(context);
        Map<String, Object> model = this.basicModel(context);

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

        Set<FisicoDTO> importadosCSV = ImportadorCSV.getInstance( filename, token).getColaboradoresDTO();
        GeneradorFisico.generar(importadosCSV.stream().toList());

        Map<String, Object> model = new HashMap<>();

        model.put("colaboradores",importadosCSV);

        context.render("Administrador/cargar-colaboradores.hbs",model);
    }

    public void show(Context context){
        this.estaLogueado(context);

        context.render("Administrador/perfilAdministrador.hbs",this.basicModel(context));
    }


}
