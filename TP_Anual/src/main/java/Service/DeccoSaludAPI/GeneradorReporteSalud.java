package Service.DeccoSaludAPI;

import Models.Domain.Tarjetas.Tarjeta;
import Models.Repository.RepoSalud;
import Service.DeccoSaludAPI.DTO.PersonaVulnerableDTO;
import Service.DeccoSaludAPI.DTO.RespuestaDTO;
import Service.DeccoSaludAPI.Mapper.PersonaVulneableMapper;
import Service.DeccoSaludAPI.Mapper.ReporteMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GeneradorReporteSalud {

    RepoSalud repo = new RepoSalud();


    public void generarReporte() throws IOException {
        List<Tarjeta> tarjetas = repo.getReporte();
        List<PersonaVulnerableDTO> listaEnviar = new ArrayList<>();

        for(Tarjeta tarjeta : tarjetas){
            PersonaVulnerableDTO personaVulnerableDTO = PersonaVulneableMapper.toDto(tarjeta);
            listaEnviar.add(personaVulnerableDTO);
        }
         List< RespuestaDTO> r = ServicioDeccoSaludAPI.getInstance().obtenerPersonasVulnerables(listaEnviar);

        if(r != null) {
            ReporteMapper reporteMapper = new ReporteMapper(r);

            repo.agregar(reporteMapper.getReporteSalud());
        }
    }


}
