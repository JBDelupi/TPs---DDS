package Controller;

import Models.Domain.Heladera.Incidentes.FallaTecnica;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.Actores.PersonaVulnerable;
import Models.Domain.Tarjetas.Tarjeta;
import Models.Repository.PseudoBaseDatosFallaTecnica;
import Models.Repository.PseudoBaseDatosUsuario;
import Service.DeccoSaludAPI.DTO.PersonaVulnerableDTO;
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
    public void index(Context context) throws IOException {
        List<RespuestaDTO> r = new ArrayList<>();

        Map<String, Object> model = this.basicModel(context);

        List<Tarjeta> tarjetas = PseudoBaseDatosUsuario.getInstance().getTarjetasAlimentar();
        List<PersonaVulnerableDTO> listaEnviar = new ArrayList<>();

        for(Tarjeta tarjeta : tarjetas){
            PersonaVulnerableDTO personaVulnerableDTO = PersonaVulneableMapper.toDto(tarjeta);
            listaEnviar.add(personaVulnerableDTO);
        }

        r = ServicioDeccoSaludAPI.getInstance().obtenerPersonasVulnerables(listaEnviar);

        model.put("localidades",r);
        context.render("DeccoSaludAPI/personasVulnerablesDeccoSalud.hbs", model);
    }
}
