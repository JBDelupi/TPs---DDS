package Controller;


import Models.Domain.Heladera.Incidentes.FallaTecnica;

import Models.Repository.PseudoBaseDatosFallaTecnica;
import Models.Repository.PseudoBaseDatosHeladera;
import io.javalin.http.Context;
import java.util.Map;


public class VisitaFallaTecnicaController extends Controller   {

    public void create(Context context) {
        this.estaLogueado(context);
        Map<String, Object> model = this.basicModel(context);
        model.put("heladeras",PseudoBaseDatosHeladera.getInstance().baseHeladeras);
        model.put("incidentesAbiertos", PseudoBaseDatosFallaTecnica.getInstance().baseFallaTecnica);
        context.render("incidentes/VisitaFallaTecnica.hbs",model);
    }

    public void save(Context context) {
        String descripcion = context.formParam("descripcion");
        String imagen = context.formParam("imagenAdjunta");
        String solucionadoStr = context.formParam("solucionado");
        Boolean solucionado = solucionadoStr.equals("si") ? true : false;
        String incidenteId = context.formParam("incidenteId");
        this.setPersona(context);

        FallaTecnica incidente = PseudoBaseDatosFallaTecnica.getInstance().getId(incidenteId);
        incidente.crearRegistroDeVisita(this.usuario, descripcion,solucionado,imagen);

        context.redirect("/incidentes");
    }


}
