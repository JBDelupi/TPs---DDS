package Controller;

import Models.Domain.Builder.IncidentesBuilder.FallaTecnicaBuilder;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Incidentes.FallaTecnica;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Fisico;
import Models.Repository.PseudoBaseDatosFallaTecnica;
import Models.Repository.PseudoBaseDatosHeladera;
import Models.Repository.PseudoBaseDatosUsuario;
import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.random.RandomGenerator;

public class FallaTecnicaController extends Controller implements ICrudViewsHandler {
    @Override
    public void create(Context context) {
        this.estaLogueado(context);
        List<Heladera> heladeras = PseudoBaseDatosHeladera.getInstance().baseHeladeras;

        Map<String, Object> model = new HashMap<>();

        model.put("heladeras",heladeras);

        context.render("incidentes/createIncidente.hbs", model);
    }

    @Override
    public void save(Context context) {
        String heladera = context.formParam("heladera");
        String descripcion = context.formParam("descripcion");
        String imagenAdjunta = context.formParam("imagenAdjunta");

        FallaTecnicaBuilder fallaTecnicaBuilder = new FallaTecnicaBuilder();
        FallaTecnica fallaTecnica = fallaTecnicaBuilder
                .heladera(PseudoBaseDatosHeladera.getInstance().getId(heladera))
                .descripcion(descripcion)
                .foto(imagenAdjunta)
                .fecha(LocalDateTime.now())
                .construir();

        fallaTecnica.setId(RandomGenerator.getDefault().nextInt());
        fallaTecnica.setColaborador((Colaborador) PseudoBaseDatosUsuario.getInstance().getId(context.sessionAttribute("idPersona")));
     //   fallaTecnica.avisarATecnico(PseudoBaseDatosUsuario.getInstance().getTecnicos());

        System.out.println("Falla Tecnica creada en heladera: " + heladera);

        PseudoBaseDatosFallaTecnica.getInstance().agregar(fallaTecnica);

        context.redirect("/incidentes");
    }

    @Override
    public void index(Context context) {
        List<FallaTecnica> fallasTecnicas = PseudoBaseDatosFallaTecnica.getInstance().baseFallaTecnica;

        Map<String, Object> model = new HashMap<>();

        model.put("fallasTecnicas",fallasTecnicas);

        context.render("incidentes/index.hbs", model);
    }

    @Override
    public void show(Context context) {
        this.estaLogueado(context);

        String id = context.pathParam("id");


        FallaTecnica fallaTecnica = PseudoBaseDatosFallaTecnica.getInstance().getId(id);

        Map<String, Object> model = new HashMap<>();

        model.put("fallaTecnica",fallaTecnica);

        context.render("incidentes/show.hbs", model);
    }

    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {

    }

    public void verSeguimiento(Context context){
        this.estaLogueado(context);

        String id = context.sessionAttribute("idPersona");
        Fisico fisico = (Fisico) PseudoBaseDatosUsuario.getInstance().getId(id);
        Map<String, Object> model = this.basicModel(context);
        model.put("humano", fisico);


        context.render("incidentes/seguimientoIncidente.hbs", model);
    }
}
