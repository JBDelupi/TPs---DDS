package Controller;

import Models.Domain.Heladera.Incidentes.FallaTecnica;
import Models.Repository.PseudoBaseDatosFallaTecnica;
import Service.DeccoSaludAPI.DTO.RespuestaDTO;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeccoSaludController {
    public void index(Context context) {
        List<RespuestaDTO> r = new ArrayList<>();

        Map<String, Object> model = new HashMap<>();

        model.put("deccosalud",r);

        context.render("DeccoSaludAPI/personasVulnerablesDeccoSalud.hbs", model);
    }
}
