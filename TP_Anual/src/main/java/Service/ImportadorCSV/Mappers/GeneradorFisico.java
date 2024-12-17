package Service.ImportadorCSV.Mappers;

import Models.Domain.Personas.Actores.Fisico;
import Models.Repository.RepoPersona;
import Service.ImportadorCSV.DTO.FisicoDTO;
import Service.Notificacion.Mensaje.MensajeBienvenida;

import java.util.List;

public class GeneradorFisico {


    public static void generar(List<FisicoDTO> fisicoDTOS){
        RepoPersona repo = new RepoPersona();
        FisicoMapper mapper = new FisicoMapper();
        fisicoDTOS.forEach(f->repo.existeUsuario(f.getMail()));
        for (FisicoDTO fisicoDTO : fisicoDTOS) {
            Fisico fisico = mapper.toEntity(fisicoDTO);
            repo.agregar(fisico);
        }
    }

}
