package Controller;


import Models.Domain.Tarjetas.Tarjeta;
import Models.Repository.RepoSalud;
import Service.DeccoSaludAPI.DTO.PersonaVulnerableDTO;
import Service.DeccoSaludAPI.DTO.Reporte.ReporteSalud;
import Service.DeccoSaludAPI.DTO.RespuestaDTO;
import Service.DeccoSaludAPI.Mapper.PersonaVulneableMapper;
import Service.DeccoSaludAPI.ServicioDeccoSaludAPI;
import io.javalin.http.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeccoSaludController extends Controller {
    private RepoSalud repoSalud;

    public DeccoSaludController(RepoSalud repoSalud) {
        this.repoSalud = repoSalud;
    }

    public void index(Context context) {
        this.estaLogueado(context);

        Map<String, Object> model = this.basicModel(context);
        List<ReporteSalud> r = repoSalud.buscarTodos();
        model.put("localidades",r);

        context.render("DeccoSaludAPI/personasVulnerablesDeccoSalud.hbs", model);
    }


}

