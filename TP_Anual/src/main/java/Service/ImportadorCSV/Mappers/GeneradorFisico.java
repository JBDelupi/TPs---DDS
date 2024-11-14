package Service.ImportadorCSV.Mappers;

import Models.Domain.Personas.Actores.Fisico;
import Models.Domain.Personas.Actores.Persona;
import Models.Repository.RepoPersona;
import Service.ImportadorCSV.DTO.FisicoDTO;

import java.util.ArrayList;
import java.util.List;

public class GeneradorFisico {


    public static void generar(List<FisicoDTO> fisicoDTOS){
        List<Fisico> fisicos = new ArrayList<>();
        RepoPersona repo = new RepoPersona(Persona.class);
        FisicoMapper mapper = new FisicoMapper();
        for (FisicoDTO fisicoDTO : fisicoDTOS) {
            Fisico fisico = mapper.toEntity(fisicoDTO);
            fisicos.add(fisico);
            repo.agregar(fisico);
        }
    }

}
